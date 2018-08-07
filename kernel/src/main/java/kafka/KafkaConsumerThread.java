package kafka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.FormulaUtil;
import common.util.InforKafkaUtil;
import common.util.JdbcUtil;
import common.util.JsonToMap;
import model.kafkadata.AlertStatus;
import model.policymaintenance.PolicyMaintenance;
import model.policyperiod.PolicyPeriod;
import model.repagentorgdetails.RepAgentOrgDetails;
import model.serverinfo.ServerInfo;
import model.superseverity.SuperSeverity;
import model.zagentcolumninfo.ZAgentColumnInfo;
import model.zagentinfo.ZAgentInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.policymaintenance.PolicyMaintenanceService;
import service.policyperiod.PolicyPeriodService;
import service.repagentorgdetails.RepAgentOrgDetailsService;
import service.serverinfo.ServerInfoService;
import service.superseverity.SuperSeverityService;
import service.zagentcolumninfo.ZAgentColumnInfoService;
import service.zagentinfo.ZAgentInfoService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 2018/5/9.
 * <p>
 * 多线程消费kafka
 */
public class KafkaConsumerThread implements Runnable {
    private final Logger L = LoggerFactory.getLogger(this.getClass());
    private Date data = new Date();
    public static final String CPU = "NT_CPUINFO";
    private static final int ZERO = 0;
    private static final String MON = "1";
    private static final String TUES = "2";
    private static final String WED = "3";
    private static final String TURS = "4";
    private static final String FRI = "5";
    private static final String SAT = "6";
    private static final String SUN = "0";
    private static String maintain = null;
    private long maintainerStartTime;
    private long maintainerEndTime;

    private ConsumerRecord<Long, String> record;

    private ServerInfoService serverInfoService;
    private ZAgentInfoService zAgentInfoService;
    private SuperSeverityService superSeverityService;
    private ZAgentColumnInfoService zAgentColumnInfoService;
    private PolicyPeriodService policyPeriodService;
    private PolicyMaintenanceService policyMaintenanceService;
    private RepAgentOrgDetailsService repAgentOrgDetailsService;

    public KafkaConsumerThread(ConsumerRecord<Long, String> record, ServerInfoService serverInfoService,ZAgentInfoService zAgentInfoService,SuperSeverityService superSeverityService,ZAgentColumnInfoService zAgentColumnInfoService,PolicyPeriodService policyPeriodService,PolicyMaintenanceService policyMaintenanceService,RepAgentOrgDetailsService repAgentOrgDetailsService) {
        this.record = record;
        this.serverInfoService = serverInfoService;
        this.zAgentInfoService = zAgentInfoService;
        this.superSeverityService = superSeverityService;
        this.zAgentColumnInfoService =zAgentColumnInfoService;
        this.policyPeriodService =policyPeriodService;
        this.policyMaintenanceService= policyMaintenanceService;
        this.repAgentOrgDetailsService =repAgentOrgDetailsService;
    }
    private void setAlertStatus(ServerInfo serverPolicy, Float loadPercentage, String thresholdA, String thresholdB, AlertStatus alertStatus) {
        System.out.println("给级别赋值12");
        String levelA, levelB;
        if (loadPercentage >= Float.valueOf(thresholdB)) {
            levelB = serverPolicy.getPolicyParams().getLevelB();
            SuperSeverity severity = new SuperSeverity();
            severity.setPaging(false);
            severity.setId(levelB);
            SuperSeverity superSeverity = superSeverityService.selectOne(severity);
            alertStatus.setSeverity(Integer.valueOf(superSeverity.getLevel()));
        } else if (loadPercentage >= Float.valueOf(thresholdA)) {
            levelA = serverPolicy.getPolicyParams().getLevelA();
            SuperSeverity severity = new SuperSeverity();
            severity.setPaging(false);
            severity.setId(levelA);
            SuperSeverity superSeverity = superSeverityService.selectOne(severity);
            alertStatus.setSeverity(Integer.valueOf(superSeverity.getLevel()));
        } else {
            System.out.println("给级别赋值34");
            alertStatus.setSeverity(0);
        }
    }
    @Override
    public void run() {
        JdbcUtil jdbcUtil = new JdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat timeFormat = new SimpleDateFormat(" HH:mm");
        // 时间戳转换成时间
        String timeToTamp = timeFormat.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        try {
            L.info("接收到监控消息:" + record.value());
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(record.value().trim());
            JsonObject recordJSON = jsonElement.getAsJsonObject();
            String host = recordJSON.get("host").getAsString();
            String hostName = recordJSON.get("hostname").getAsString();
            String timestamp = recordJSON.get("timestamp").getAsString();
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setPaging(false);
            serverInfo.setServerIp(host);

            List<ServerInfo> serverInfoList = serverInfoService.getPolicyInfo(serverInfo);
            PreparedStatement ps = null;
            float result=0.0f;
            if(serverInfoList != null && !serverInfoList.isEmpty()){
                ServerInfo serverIn = serverInfoService.selectOne(serverInfo);
                for (ServerInfo kl  :serverInfoList) {
                    kl.getZAgentInfos().getIndexShorthand();
                    ZAgentColumnInfo zAgentColumnInfo = new ZAgentColumnInfo();
                    zAgentColumnInfo.setPaging(false);
                    System.out.println("得到关键字"+ kl.getZAgentInfos().getIndexShorthand());
                    String l = recordJSON.getAsJsonObject( kl.getZAgentInfos().getIndexShorthand()).toString();
                    // System.out.println(l+"nm");
                    //  System.out.println(kl.getZAgentInfos().getIndexName()+"得到id");
                    JsonElement jsonElement1 = jsonParser.parse(l);
                    JsonObject recordJSON1 = jsonElement1.getAsJsonObject();
                    for (String in : JsonToMap.toMap(l).keySet()) {
                        String re = recordJSON1.getAsJsonObject(in).toString();
                        // System.out.println(in.toString()+"得到key值");
                        JsonElement jsonElemen = jsonParser.parse(re);
                        JsonObject recordJSO = jsonElemen.getAsJsonObject();
                        //  System.out.println(kl.getZAgentInfos().getCalculationFormula()+"最终要获取的值");
                        zAgentColumnInfo.setAgentId(kl.getZAgentInfos().getIndexName());
                        //  System.out.println(kl.getZAgentInfos().getId()+"得到knm");
                        List<ZAgentColumnInfo> zAgentColumnInfoList = zAgentColumnInfoService.selectList(zAgentColumnInfo);
                        String  calculationFormula = kl.getZAgentInfos().getCalculationFormula();
                        for (ZAgentColumnInfo zAgentColumnInf : zAgentColumnInfoList) {
                            //System.out.println(zAgentColumnInf.getColumnName()+"得到key值-------------------------------");
                            if (zAgentColumnInf.getColumnName()=="host" || zAgentColumnInf.getColumnName()=="hostname"|| zAgentColumnInf.getColumnName()=="timestamp"){
                                System.out.println("无需读取该数据");
                            } else {
                                //    System.out.println(zAgentColumnInf.getColumnName()+"需要读取的数据key值");
                                try {
                                    if (recordJSO.get(zAgentColumnInf.getColumnName()).getAsString() != null && !recordJSO.get(zAgentColumnInf.getColumnName()).getAsString().isEmpty()) {
                                        //    System.out.println("得到内存数据" + recordJSO.get(zAgentColumnInf.getColumnName()).getAsString());
                                        //替换对应的指标
                                        calculationFormula = calculationFormula.replace("$"+zAgentColumnInf.getId(),  recordJSO.get(zAgentColumnInf.getColumnName()).getAsString());
                                        if (!calculationFormula.contains("$")){
                                            if(calculationFormula.contains("/")){
                                                // System.out.println("得到内fg存"+FormulaUtil.calc(calculationFormula));
                                                float f = 0.1f;
                                                DecimalFormat df = new DecimalFormat("#########.#");
                                                String  rs = df.format(Float.parseFloat(FormulaUtil.calc(calculationFormula))+f);
                                                result= Float.parseFloat(rs)*100;
                                            }else{
                                                result = Float.parseFloat(FormulaUtil.calc(calculationFormula));
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("数据为空");
                                }
                            }
                        }
                    }
                    //omnibus数据插入
                    AlertStatus alertStatus = new AlertStatus();
                    alertStatus.setNode(host);
                    alertStatus.setNodeAlias(hostName);
                    alertStatus.setServerName(hostName);
                    alertStatus.setFirstOccurrence(DateTime.parse(timestamp).getMillis() / 1000);
                    alertStatus.setAlertKey(kl.getZAgentInfos().getKeyWord());
                        alertStatus.setDevDesc(kl.getZAgentInfos().getContentDescribe());

//                    alertStatus.setAPPLICATION_SYSTEM(serverIn.getApplicationSystem());
//                    System.out.println( "1"+serverIn.getApplicationSystem());
//                    alertStatus.setSERVER_NAME( serverIn.getServerName());
//                    System.out.println( "2"+serverIn.getServerName());
//                    alertStatus.setSTATE_NATURE(serverIn.getStateNature());
//                    System.out.println( "3"+serverIn.getStateNature());
//                    alertStatus.setSERVER_USE(serverIn.getServerUse());
//                    System.out.println( "4"+serverIn.getServerUse());
//                    alertStatus.setDEPARTMENT(serverIn.getDepartment());
//                    System.out.println( "5"+serverIn.getDepartment());
//                    alertStatus.setATTRIBUTE_ORGANIZATION(serverIn.getAttributeOrganization());
//                    System.out.println( "6"+serverIn.getAttributeOrganization());
//                    alertStatus.setVERSION(serverIn.getVersion());
//                    System.out.println( "7"+serverIn.getVersion());
//                    alertStatus.setFIRM(serverIn.getFirm());
//                    System.out.println( "8"+serverIn.getFirm());
//                    alertStatus.setCOMPUTER_ROOM(serverIn.getComputerRoom());
//                    System.out.println( "9"+serverIn.getComputerRoom());
//                    alertStatus.setNETWORK_ZONE(serverIn.getNetworkZone());
//                    System.out.println( "10"+serverIn.getNetworkZone());
//                    alertStatus.setASSET_ID(serverIn.getAssetId());
//                    System.out.println( "11"+serverIn.getAssetId());
//                    alertStatus.setCOMPUTER_COUNTER(serverIn.getComputerCounter());
//                    System.out.println( "12"+serverIn.getComputerCounter());
//                    alertStatus.setMAINTAINER(serverIn.getMaintainer());
//                    System.out.println( "13"+serverIn.getMaintainer());
//                    alertStatus.setTIME_TACTICS(serverIn.getTimeTactics());
//                    System.out.println( "14"+serverIn.getTimeTactics());
//                    alertStatus.setSERVER_TACTICS(serverIn.getServerTactics());
//                    System.out.println( "15"+serverIn.getServerTactics());
//                    alertStatus.setOS_TYPE(serverIn.getOsType());
//                    System.out.println( "16"+serverIn.getOsType());
//                    alertStatus.setOS_DETAIL_VERSION(serverIn.getOsDetailVersion());
//                    System.out.println( "17"+serverIn.getOsDetailVersion());
//                    alertStatus.setOS_BIT(serverIn.getOsBit());
//                    System.out.println( "18"+serverIn.getOsBit());
//                    alertStatus.setOS_PATCH_VERSION(serverIn.getOsPatchVersion());
//                    System.out.println( "19"+serverIn.getOsPatchVersion());
//                    alertStatus.setOS_CPU(serverIn.getOsCpu());
//                    System.out.println( "20"+serverIn.getOsCpu());
//                    alertStatus.setOS_MANAGE_GROUP(serverIn.getOsManageGroup());
//                    System.out.println( "21"+serverIn.getOsManageGroup());
//                    alertStatus.setOS_MEMORY(serverIn.getOsMemory());
//                    System.out.println( "22"+serverIn.getOsMemory());
//                    alertStatus.setDB_TYPE(serverIn.getDbType());
//                    System.out.println( "23"+serverIn.getDbType());
//                    alertStatus.setDB_BIT(serverIn.getDbBit());
//                    System.out.println( "24"+serverIn.getDbBit());
//                    alertStatus.setDB_MANAGE_GROUP(serverIn.getDbManageGroup());
//                    System.out.println( "25"+serverIn.getDbManageGroup());
//                    alertStatus.setMW_TYPE(serverIn.getMwType());
//                    System.out.println( "26"+serverIn.getMwType());
//                    alertStatus.setMW_VERSION(serverIn.getMwVersion());
//                    System.out.println( "27"+serverIn.getMwVersion());
//                    alertStatus.setMW_BIT(serverIn.getMwBit());
//                    System.out.println( "28"+serverIn.getMwBit());
//                    alertStatus.setMW_MANAGE_GROUP(serverIn.getMwManageGroup());
//                    System.out.println( "29"+serverIn.getMwManageGroup());
//                    String APPLICATION_SYSTEM=alertStatus.getAPPLICATION_SYSTEM();
//                    String  SERVER_NAME=alertStatus.getSERVER_NAME();
//                    String STATE_NATURE= alertStatus.getSTATE_NATURE();
//                    String SERVER_USE =alertStatus.getSERVER_USE();
//                    String DEPARTMENT= alertStatus.getDEPARTMENT();
//
//                    String ATTRIBUTE_ORGANIZATION= alertStatus.getATTRIBUTE_ORGANIZATION();
//                    String VERSION = alertStatus.getVERSION();
//                    String FIRM=alertStatus.getFIRM();
//                    String COMPUTER_ROOM=alertStatus.getCOMPUTER_ROOM();
//                    String NETWORK_ZONE=alertStatus.getNETWORK_ZONE();
//
//                    String ASSET_ID= alertStatus.getASSET_ID();
//                    String COMPUTER_COUNTER= alertStatus.getCOMPUTER_COUNTER();
//                    String MAINTAINER= alertStatus.getMAINTAINER();
//                    String TIME_TACTICS= alertStatus.getTIME_TACTICS();
//                    String SERVER_TACTICS= alertStatus.getSERVER_TACTICS();
//
//                    String OS_TYPE=  alertStatus.getOS_TYPE();
//                    String OS_DETAIL_VERSION= alertStatus.getOS_DETAIL_VERSION();
//                    String OS_BIT= alertStatus.getOS_BIT();
//                    String OS_PATCH_VERSION=alertStatus.getOS_PATCH_VERSION();
//                    String OS_CPU=alertStatus.getOS_CPU();
//
//                    String OS_MANAGE_GROUP= alertStatus.getOS_MANAGE_GROUP();
//                    String OS_MEMORY=  alertStatus.getOS_MEMORY();
//                    String DB_TYPE= alertStatus.getDB_TYPE();
//                    String DB_BIT=  alertStatus.getDB_BIT();
//                    String DB_MANAGE_GROUP=  alertStatus.getDB_MANAGE_GROUP();
//
//                    String MW_TYPE=  alertStatus.getMW_TYPE();
//                    String MW_VERSION=  alertStatus.getMW_VERSION();
//                    String MW_BIT= alertStatus.getMW_BIT();
//                    String MW_MANAGE_GROUP= alertStatus.getMW_MANAGE_GROUP();


                    RepAgentOrgDetails repAgentOrgDetails = new RepAgentOrgDetails();
                    //  mysql采集管理信息记录
                    repAgentOrgDetails.setNode(host);
                   repAgentOrgDetails.setResult(String.valueOf(result));
                   repAgentOrgDetails.setKpi(kl.getZAgentInfos().getIndexName());
                  repAgentOrgDetails.setAgent(kl.getZAgentInfos().getIndexShorthand().substring(0,2));
                  repAgentOrgDetails.setAlertgroup(kl.getZAgentInfos().getKeyWord());
                    repAgentOrgDetails.setAlertkey(kl.getZAgentInfos().getIndexShorthand());

                    int  nm =   repAgentOrgDetailsService.insert(repAgentOrgDetails);
                    ZAgentInfo zAgentInfo = new ZAgentInfo();
                    zAgentInfo.setPaging(false);
                    //  System.out.println(zAgentInfoService.selectList(zAgentInfo).size());
                    PolicyPeriod policyPeriod = new PolicyPeriod();
                    policyPeriod.setPaging(false);
                    ServerInfo   serverInfo1= serverInfoService.selectOne(serverInfo);
                    if (serverInfoService.selectOne(serverInfo).getTimeTactics() != null && !serverInfoService.selectOne(serverInfo).getTimeTactics().isEmpty()) { alertStatus.setAPPLICATION_SYSTEM(serverInfo1.getApplicationSystem());
                        policyPeriod.setId(serverInfoService.selectOne(serverInfo).getTimeTactics());
                        PolicyPeriod getPolicyPeriod = policyPeriodService.selectOne(policyPeriod);
                        //维护期时间查询
                        if (serverInfoService.selectOne(serverInfo).getMaintainer() == null) {
                            maintain = "false";
                        } else {
                            String maintainer = serverInfoService.selectOne(serverInfo).getMaintainer();
                            PolicyMaintenance policyMaintenance = new PolicyMaintenance();
                            policyMaintenance.setId(maintainer);
                            PolicyMaintenance PolicyMaintenanceService = policyMaintenanceService.selectOne(policyMaintenance);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:s");
                            maintain =PolicyMaintenanceService.getStatus();

                            try {
                                maintainerStartTime = simpleDateFormat.parse(PolicyMaintenanceService.getStartDate()).getTime();
                                maintainerEndTime = simpleDateFormat.parse(PolicyMaintenanceService.getEndDate()).getTime();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                        String thresholdA = kl.getPolicyParams().getThresholdA();
                        String thresholdB = kl.getPolicyParams().getThresholdB();
                        //   System.out.println(InforKafkaUtil.getWeekOfDate(data)+"得到星期");
                        System.out.println(maintain+"维护期转态");
//                        if(maintain.equals("false")){
//                            System.out.println(maintain=="false"+"是否为判断值");
//                        }
                        System.out.println(InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod1(), getPolicyPeriod.getEndperiod1())+"监控时间段");
                        //   System.out.println("是否开启监控"+getPolicyPeriod.getPeriod1status());
                        switch (InforKafkaUtil.getWeekOfDate(data)) {
                            case MON:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod1(), getPolicyPeriod.getEndperiod1())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                } else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case TUES:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod2status()) && getPolicyPeriod.getPeriod1status() != null) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod2(), getPolicyPeriod.getEndperiod2())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                } else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case WED:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod3status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod3(), getPolicyPeriod.getEndperiod3())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                        alertStatus.setSeverity(1);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                }else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case TURS:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod4status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod4(), getPolicyPeriod.getEndperiod4())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                        alertStatus.setSeverity(1);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                }else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case FRI:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod5status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod5(), getPolicyPeriod.getEndperiod5())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                        alertStatus.setSeverity(1);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                }else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case SAT:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod6status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod6(), getPolicyPeriod.getEndperiod6())) {
                                        setAlertStatus(kl,result, thresholdA, thresholdB, alertStatus);

                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                }else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            case SUN:
                                alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + kl.getZAgentInfos().getIndexShorthand());
                                alertStatus.setSummary(kl.getZAgentInfos().getContentDescribe() + result + kl.getZAgentInfos().getUnit());
                                if ("TRUE".equals(getPolicyPeriod.getPeriod7status())) {
                                    if (maintain.equals("false") && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod7(), getPolicyPeriod.getEndperiod7())) {
                                        setAlertStatus(kl, result, thresholdA, thresholdB, alertStatus);
                                    } else {
                                        alertStatus.setSeverity(1);
                                    }
                                }else {
                                    alertStatus.setSeverity(1);
                                }
                                break;
                            default:
                        }
                    }

                    //   String p="'"+APPLICATION_SYSTEM+"'"  +","+"'"+SERVER_NAME+"'"+","+ "'"+STATE_NATURE+"'"+","+"'"+SERVER_USE+"'"+","+"'"+ DEPARTMENT+"'";


                    //   String p1=,'APPLICATION_SYSTEM'"APPLICATION_SYSTEM"+","+"SERVER_NAME"+","+"STATE_NATURE"+","+"SERVER_USE"+"DEPARTMENT";

                    //    String q="'"+ATTRIBUTE_ORGANIZATION+"'"  +","+"'"+VERSION+"'"+","+ "'"+FIRM+"'"+","+"'"+COMPUTER_ROOM+"'"+","+"'"+ NETWORK_ZONE+"'";
                    //    String q1="ATTRIBUTE_ORGANIZATION"+","+"VERSION"+","+"FIRM"+","+"COMPUTER_ROOM"+"NETWORK_ZONE";

//                       String r="'"+ASSET_ID+"'"  +","+"'"+COMPUTER_COUNTER+"'"+","+ "'"+MAINTAINER+"'"+","+"'"+TIME_TACTICS+"'"+","+"'"+ SERVER_TACTICS+"'";
//                        String r1="ASSET_ID"+","+"COMPUTER_COUNTER"+","+"MAINTAINER"+","+"TIME_TACTICS"+"SERVER_TACTICS";
//
//                        String s="'"+OS_TYPE+"'"  +","+"'"+OS_DETAIL_VERSION+"'"+","+ "'"+OS_BIT+"'"+","+"'"+OS_PATCH_VERSION+"'"+","+"'"+  OS_CPU+"'";;
//                      String s1="OS_TYPE"+","+"OS_DETAIL_VERSION"+","+"OS_BIT"+","+"OS_PATCH_VERSION"+"OS_CPU";
//
//                       String t="'"+OS_MANAGE_GROUP+"'"  +","+"'"+OS_MEMORY+"'"+","+ "'"+DB_TYPE+"'"+","+"'"+DB_BIT+"'"+","+"'"+DB_MANAGE_GROUP+"'";
//                      String t1="OS_MANAGE_GROUP"+","+"OS_MEMORY"+","+"DB_TYPE"+","+"DB_BIT"+"DB_MANAGE_GROUP";
//
//                       String u="'"+MW_TYPE+"'"  +","+"'"+MW_VERSION+"'"+","+ "'"+MW_BIT+"'"+","+"'"+MW_MANAGE_GROUP+"'";
//                      String u1="MW_TYPE"+","+"MW_VERSION"+","+"MW_BIT"+","+"MW_MANAGE_GROUP";
//
//                      String text=p+","+q+","+r+","+s+","+t+","+u;
//
//                      String hello=p1+","+q1+","+r1+","+s1+","+t1+","+u1;
//
//                        System.out.println(text+"得到sql"+hello);

                    String identifer = alertStatus.getIdentifier();
                    Long longTime = alertStatus.getFirstOccurrence();
                    String node = alertStatus.getNode();
                    String nodeAlias = alertStatus.getNodeAlias();
                    String summary = alertStatus.getSummary();
                    int severity = alertStatus.getSeverity();
                      String  devDesc   =alertStatus.getDevDesc();
                    String  alertKey  =alertStatus.getAlertKey();
                    //  String sql = " INSERT INTO alerts.status (Identifier,FirstOccurrence,Node,NodeAlias,Severity,Summary)VALUES ('" + identifer + "'," + longTime + ",'" + node + "','" + nodeAlias + "'," + severity + ",'" + summary + "')";
                    String sql = " INSERT INTO alerts.status (Identifier,FirstOccurrence,Node,NodeAlias,Severity,Summary,AlertKey,DevDesc) " +
                            "VALUES ('" + identifer + "'," + longTime + ",'" + node + "','" + nodeAlias + "'," + severity + ",'" + summary + "','" +alertKey +"','" +devDesc +"')";
                    L.info("执行sql：" + sql);
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                }
            }else {
                L.info("服务器：" + host +" 未分配策略");
            }
        } catch (Exception e) {
            L.error("发送报文异常", e);
        } finally {
            jdbcUtil.releaseConn();
        }
    }
}

