package kafka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.JdbcUtil;
import model.kafkadata.AlertStatus;
import model.repagentorgdetails.RepAgentOrgDetails;
import model.serverinfo.ServerInfo;
import model.superseverity.SuperSeverity;
import model.zagentinfo.ZAgentInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.repagentorgdetails.RepAgentOrgDetailsService;
import service.serverinfo.ServerInfoService;
import service.superseverity.SuperSeverityService;
import service.zagentinfo.ZAgentInfoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by xj on 2018/5/9.
 * <p>
 * 多线程消费kafka
 */
public class KafkaConsumerThread implements Runnable {
    private final Logger L = LoggerFactory.getLogger(this.getClass());

    public static final String CPU = "NT_CPUINFO";
    public static final String NETINT = "NT_NETINTINFO";
    public static final String PHYMEM = "NT_PHYMEMINFO";
    public static final String USER = "NT_USERINFO";
    public static final String PROCESS = "NT_PROCESSINFO";
    public static final String SWAP = "NT_SWAPINFO";
    public static final String DISK = "NT_DISKINFO";
    public static final String SYS = "NT_SYSINFO";

    private ConsumerRecord<Long, String> record;

    private ServerInfoService serverInfoService;
    private ZAgentInfoService zAgentInfoService;
    private SuperSeverityService superSeverityService;
    private RepAgentOrgDetailsService repAgentOrgDetailsService;


    public KafkaConsumerThread(ConsumerRecord<Long, String> record, ServerInfoService serverInfoService,ZAgentInfoService zAgentInfoService,SuperSeverityService superSeverityService,RepAgentOrgDetailsService repAgentOrgDetailsService) {
        this.record = record;
        this.serverInfoService = serverInfoService;
        this.zAgentInfoService = zAgentInfoService;
        this.superSeverityService = superSeverityService;
        this.repAgentOrgDetailsService = repAgentOrgDetailsService;
    }

    @Override
    public void run() {
        System.out.println("wwwwrrrrrrrrrr");
        JdbcUtil jdbcUtil = new JdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        RepAgentOrgDetails  repAgentOrgDetails=new RepAgentOrgDetails();
        repAgentOrgDetails.setAgent("1234");
        repAgentOrgDetails.setAlertgroup("1234");
        System.out.println("wwwwzzzzzzzzzzzzzrrrrrrrrr");
        repAgentOrgDetails.setAlertkey("1234");
        repAgentOrgDetails.setKpi("1234");
        repAgentOrgDetails.setNode("1234");
        repAgentOrgDetails.setResult("1234");
        int l=repAgentOrgDetailsService.insert(repAgentOrgDetails);
        System.out.println(l+"rrrrrrrrrrrrrrrrrrrrrrrrr");
        try {
            System.out.println("rrrrrrrrrrrrrgggrrrrrrrrrr");
            L.info("接收到监控消息:" + record.value());
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(record.value().trim());
            JsonObject recordJSON = jsonElement.getAsJsonObject();
            //System.out.println(lineLogJSON.getAsJsonObject("NT_CPUINFO"));
            String host = recordJSON.get("host").getAsString();
            String hostName = recordJSON.get("hostname").getAsString();
            String timestamp = recordJSON.get("timestamp").getAsString();
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setPaging(false);
           // serverInfo.setServerIp(host);
            serverInfo.setServerIp("11.24.115.232");
            List<ServerInfo> serverInfoList = serverInfoService.getPolicyInfo(serverInfo);
            for ( ServerInfo   t :serverInfoList){
                System.out.println(t.getAssetId()+"kkkk5");
            }
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
                //zAgentInfoService.selectList(zAgentInfo).forEach(agentInfo -> {
                for(ZAgentInfo agentInfo : zAgentInfoService.selectList(zAgentInfo)){
                    switch (agentInfo.getIndexShorthand()) {
                        case CPU:
                            Float loadPercentage = recordJSON.getAsJsonObject("NT_CPUINFO").getAsJsonObject("win32_Processor").get("LoadPercentage").getAsFloat();
                            L.info("CPU使用率：" + loadPercentage);
                            for (ServerInfo serverPolicy : serverInfoList) {
//                                            serverInfoList.forEach(serverPolicy -> {
                                if (serverPolicy.getZAgentInfos().getIndexShorthand().equals(CPU)) {
                                    alertStatus.setIdentifier(hostName + ":" + timestamp + ":" + CPU);
                                    alertStatus.setSummary(serverPolicy.getZAgentInfos().getContentDescribe() + loadPercentage + serverPolicy.getZAgentInfos().getUnit());
                                    String thresholdA = serverPolicy.getPolicyParams().getThresholdA();
                                    String thresholdB = serverPolicy.getPolicyParams().getThresholdB();
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

                            }
                            break;
                        case NETINT:
                            break;
                        case PHYMEM:
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
                String identifer = alertStatus.getIdentifier();
                Long longTime = alertStatus.getFirstOccurrence();
                String node = alertStatus.getNode();
                String nodeAlias = alertStatus.getNodeAlias();
                String summary = alertStatus.getSummary();
                int severity = alertStatus.getSeverity();
                String sql = " INSERT INTO alerts.status (Identifier,FirstOccurrence,Node,NodeAlias,Severity,Summary)VALUES ('" + identifer + "'," + longTime + ",'" + node + "','" + nodeAlias + "'," + severity + ",'" + summary + "')";
                L.info("执行sql：" + sql);
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
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

