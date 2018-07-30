package kafka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.InforKafkaUtil;
import common.util.JdbcUtil;
import model.kafkadata.AlertStatus;
import model.policymaintenance.PolicyMaintenance;
import model.policyperiod.PolicyPeriod;
import model.repagentorgdetails.RepAgentOrgDetails;
import model.serverinfo.ServerInfo;
import model.superseverity.SuperSeverity;
import model.zagentinfo.ZAgentInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.policymaintenance.PolicyMaintenanceService;
import service.policyperiod.PolicyPeriodService;
import service.repagentorgdetails.RepAgentOrgDetailsService;
import service.serverinfo.ServerInfoService;
import service.serverinfo.impl.ServerInfoServiceImpl;
import service.superseverity.SuperSeverityService;
import service.zagentinfo.ZAgentInfoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public static final String NETINT = "NT_NETINTINFO";
    public static final String PHYMEM = "NT_PHYMEMINFO";
    public static final String USER = "NT_USERINFO";
    public static final String PROCESS = "NT_PROCESSINFO";
    public static final String SWAP = "NT_SWAPINFO";
    public static final String DISK = "NT_DISKINFO";
    public static final String SYS = "NT_SYSINFO";
    private static final int ZERO = 0;
    private static final String MON = "1";
    private static final String TUES = "2";
    private static final String WED = "3";
    private static final String TURS = "4";
    private static final String FRI = "5";
    private static final String SAT = "6";
    private static final String SUN = "7";
    private  static boolean maintain=false;
    private long maintainerStartTime;
    private long maintainerEndTime;

    private ConsumerRecord<Long, String> record;
    private ServerInfoService serverInfoService;
    private ZAgentInfoService zAgentInfoService;
    private SuperSeverityService superSeverityService;
    private RepAgentOrgDetailsService repAgentOrgDetailsService;
    private PolicyPeriodService policyPeriodService;
    private PolicyMaintenanceService policyMaintenanceService;

    public KafkaConsumerThread(ConsumerRecord<Long, String> record, ZAgentInfoService zAgentInfoService, SuperSeverityService superSeverityService, PolicyPeriodService policyPeriodService, PolicyMaintenanceService policyMaintenanceService, ServerInfoService serverInfoService, RepAgentOrgDetailsService repAgentOrgDetailsService) {
        this.record = record;
        this.repAgentOrgDetailsService = repAgentOrgDetailsService;
        this.policyPeriodService = policyPeriodService;
        this.zAgentInfoService = zAgentInfoService;
        this.superSeverityService = superSeverityService;
        this.policyPeriodService = policyPeriodService;
        this.policyMaintenanceService = policyMaintenanceService;
        this.serverInfoService = serverInfoService;
    }
    private void setAlertStatus(ServerInfo serverPolicy,Float loadPercentage,  String thresholdA , String thresholdB,AlertStatus alertStatus) {
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
            alertStatus.setSeverity(0);
        }
    }




    @Override
    public void run() {
//        JdbcUtil jdbcUtil = new JdbcUtil();
//        Connection conn = jdbcUtil.getConnection();
        //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat timeFormat = new SimpleDateFormat(" HH:mm");
        // 时间戳转换成时间
        String timeToTamp = timeFormat.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        RepAgentOrgDetails  repAgentOrgDetails  = new RepAgentOrgDetails();
        try {
            L.info("接收到监控消息:" + record.value());
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(record.value().trim());
            JsonObject recordJSON = jsonElement.getAsJsonObject();
          //  String host = recordJSON.get("host").getAsString();
            String   host ="8.8.8.8";
            String hostName = recordJSON.get("hostname").getAsString();
            String timestamp = recordJSON.get("timestamp").getAsString();
           //mysql采集管理信息记录
            repAgentOrgDetails.setNode("8.8.8.8");
            repAgentOrgDetails.setResult("hahah");
            repAgentOrgDetails.setKpi("hahaha");
            repAgentOrgDetails.setAgent("windows");
            repAgentOrgDetails.setAlertkey("hello");
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setPaging(false);
            serverInfo.setServerIp(host);
            if (serverInfoService.selectOne(serverInfo).getMaintainer()==null){
                maintain =true;
            }else{
                String maintainer = serverInfoService.selectOne(serverInfo).getMaintainer();
                PolicyMaintenance policyMaintenance = new PolicyMaintenance();
                policyMaintenance.setId(maintainer);
                PolicyMaintenance PolicyMaintenanceService = policyMaintenanceService.selectOne(policyMaintenance);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:s");
                try {
                    maintainerStartTime = simpleDateFormat.parse(PolicyMaintenanceService.getStartDate()).getTime();
                    maintainerEndTime = simpleDateFormat.parse(PolicyMaintenanceService.getEndDate()).getTime();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
            List<ServerInfo> serverInfoList = serverInfoService.getPolicyInfo(serverInfo);
            PreparedStatement ps = null;
            if(serverInfoList != null && !serverInfoList.isEmpty()){
                //omnibus数据插入
                AlertStatus alertStatus = new AlertStatus();
                alertStatus.setNode(host);
                alertStatus.setNodeAlias(hostName);
                alertStatus.setServerName(hostName);
                alertStatus.setFirstOccurrence(DateTime.parse(timestamp).getMillis() / 1000);
                ZAgentInfo zAgentInfo = new ZAgentInfo();
                zAgentInfo.setPaging(false);
                System.out.println(zAgentInfoService.selectList(zAgentInfo).size());
                PolicyPeriod   policyPeriod  = new PolicyPeriod();
                 policyPeriod.setPeriodName("test1");
                PolicyPeriod getPolicyPeriod = policyPeriodService.selectOne(policyPeriod);
                for(ZAgentInfo agentInfo : zAgentInfoService.selectList(zAgentInfo)){
                    switch (agentInfo.getIndexShorthand()) {
                        case CPU:
                            Float loadPercentage = recordJSON.getAsJsonObject("NT_CPUINFO").getAsJsonObject("win32_Processor").get("LoadPercentage").getAsFloat();
                            L.info("CPU使用率：" + loadPercentage);
                            repAgentOrgDetails.setResult(CPU);
                            repAgentOrgDetailsService.insert(repAgentOrgDetails);
                            for (ServerInfo serverPolicy : serverInfoList) {
                                    if (serverPolicy.getZAgentInfos().getIndexShorthand().equals(CPU)) {
                                        alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + CPU);
                                        alertStatus.setSummary(serverPolicy.getZAgentInfos().getContentDescribe() + loadPercentage + serverPolicy.getZAgentInfos().getUnit());
                                        String thresholdA = serverPolicy.getPolicyParams().getThresholdA();
                                        String thresholdB = serverPolicy.getPolicyParams().getThresholdB();
                                        switch (InforKafkaUtil.getWeekOfDate(data)) {
                                            case MON:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod1(), getPolicyPeriod.getEndperiod1())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case TUES:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod2(), getPolicyPeriod.getEndperiod2())) {
                                                    setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                } else {
                                                    alertStatus.setSeverity(0);
                                                }
                                            }else{
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case WED:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod3(), getPolicyPeriod.getEndperiod3())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }
                                                break;
                                            case TURS:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod4(), getPolicyPeriod.getEndperiod4())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }
                                                break;
                                            case FRI:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod5(), getPolicyPeriod.getEndperiod5())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }
                                                break;
                                            case SAT:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod6(), getPolicyPeriod.getEndperiod6())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }
                                                break;
                                            case SUN:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod7(), getPolicyPeriod.getEndperiod7())) {
                                                        setAlertStatus(serverPolicy, loadPercentage,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }
                                                break;
                                            default:
                                        }
                                    }
                            }
                            break;
                        case NETINT:
                            break;
                        case PHYMEM:
                            Float FreeMeminfo = recordJSON.getAsJsonObject("NT_PHYMEMINFO").getAsJsonObject("Win32_OperatingSystem").get("FreePhysicalMemory").getAsFloat();
                            Float TotalMeminfo  = recordJSON.getAsJsonObject("NT_PHYMEMINFO").getAsJsonObject("Win32_OperatingSystem").get("TotalVisibleMemorySize").getAsFloat();
                            Float loadPercentAge =  1-FreeMeminfo/TotalMeminfo;
                            L.info("内存使用率：" +  loadPercentAge );
                            repAgentOrgDetails.setResult(PHYMEM);
                            repAgentOrgDetailsService.insert(repAgentOrgDetails);
                            for (ServerInfo serverPolicy : serverInfoList) {
                                    if (serverPolicy.getZAgentInfos().getIndexShorthand().equals(PHYMEM)) {
                                        alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + PHYMEM);
                                        alertStatus.setSummary(serverPolicy.getZAgentInfos().getContentDescribe() + loadPercentAge + serverPolicy.getZAgentInfos().getUnit());
                                        String thresholdA = serverPolicy.getPolicyParams().getThresholdA();
                                        String thresholdB = serverPolicy.getPolicyParams().getThresholdB();
                                        switch (InforKafkaUtil.getWeekOfDate(data)) {
                                            case MON:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod1(), getPolicyPeriod.getEndperiod1())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case TUES:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod2(), getPolicyPeriod.getEndperiod2())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case WED:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod3(), getPolicyPeriod.getEndperiod3())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case TURS:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod4(), getPolicyPeriod.getEndperiod4())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case FRI:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod5(), getPolicyPeriod.getEndperiod5())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case SAT:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod6(), getPolicyPeriod.getEndperiod6())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            case SUN:
                                                if ("TRUE".equals(getPolicyPeriod.getPeriod1status())) {
                                                    if (maintain && InforKafkaUtil.time(timeToTamp, getPolicyPeriod.getStartperiod7(), getPolicyPeriod.getEndperiod7())) {
                                                        setAlertStatus(serverPolicy, loadPercentAge,   thresholdA ,  thresholdB, alertStatus);
                                                    } else {
                                                        alertStatus.setSeverity(0);
                                                    }
                                                }else {
                                                    alertStatus.setSeverity(0);
                                                }
                                                break;
                                            default:
                                        }
                                    }
                                }
                            System.out.println("监控物理内存"+TotalMeminfo);
                            break;
                        case USER:
                            break;
                        case PROCESS:
                            break;
                        case SWAP:
                            break;
                        case DISK:
                            break;
                        case SYS:
                            break;
                    }
                }
                System.out.println("one"+alertStatus.getIdentifier()+"two"+alertStatus.getFirstOccurrence()+"three"+alertStatus.getFirstOccurrence()+"four"+alertStatus.getNodeAlias()+"five"+alertStatus.getSummary());
                String identifer = alertStatus.getIdentifier();
                Long longTime = alertStatus.getFirstOccurrence();
                String node = alertStatus.getNode();
                String nodeAlias = alertStatus.getNodeAlias();
                String summary = alertStatus.getSummary();
                int severity = alertStatus.getSeverity();
                String sql = " INSERT INTO alerts.status (Identifier,FirstOccurrence,Node,NodeAlias,Severity,Summary)VALUES ('" + identifer + "'," + longTime + ",'" + node + "','" + nodeAlias + "'," + severity + ",'" + summary + "')";
                L.info("执行sql：" + sql);
//                ps = conn.prepareStatement(sql);
//                ps.executeUpdate();
            }else {
                L.info("服务器：" + host +" 未分配策略");
            }
        } catch (Exception e) {
            L.error("发送报文异常", e);
        }
    }
  }