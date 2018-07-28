package model.repagentorgdetails;

import model.BaseModel;
import org.apache.ibatis.type.Alias;

/**
 * @Created with IDEA
 * @author:LiWangZhou
 * @Date:2018/7/27/027
 * @Time:13:06
 **/
@Alias(" RepAgentOrgDetails")
public class RepAgentOrgDetails extends BaseModel {
    private String node;

    private String agent;

    private String alertgroup;

    private String alertkey;

    private String kpi;

    private String result;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAlertgroup() {
        return alertgroup;
    }

    public void setAlertgroup(String alertgroup) {
        this.alertgroup = alertgroup;
    }

    public String getAlertkey() {
        return alertkey;
    }

    public void setAlertkey(String alertkey) {
        this.alertkey = alertkey;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
