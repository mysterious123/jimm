package model.kafkadata;

import model.BaseModel;

/**
 * Created by Administrator on 2018/5/4.
 */
public class AlertStatus extends BaseModel{
    private String Identifier;
    private String AlertGroup;
    private String Acknowledged;
    private String Agent;
    private String AlertKey;
    private Long FirstOccurrence;
    private Long LastOccurrence;
    private String Node;
    private String NodeAlias;
    private String ServerName;
    private Integer Severity;
    private String Summary;

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getAlertGroup() {
        return AlertGroup;
    }

    public void setAlertGroup(String alertGroup) {
        AlertGroup = alertGroup;
    }

    public String getAcknowledged() {
        return Acknowledged;
    }

    public void setAcknowledged(String acknowledged) {
        Acknowledged = acknowledged;
    }

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public String getAlertKey() {
        return AlertKey;
    }

    public void setAlertKey(String alertKey) {
        AlertKey = alertKey;
    }

    public Long getFirstOccurrence() {
        return FirstOccurrence;
    }

    public void setFirstOccurrence(Long firstOccurrence) {
        FirstOccurrence = firstOccurrence;
    }

    public Long getLastOccurrence() {
        return LastOccurrence;
    }

    public void setLastOccurrence(Long lastOccurrence) {
        LastOccurrence = lastOccurrence;
    }

    public String getNode() {
        return Node;
    }

    public void setNode(String node) {
        Node = node;
    }

    public String getNodeAlias() {
        return NodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        NodeAlias = nodeAlias;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public Integer getSeverity() {
        return Severity;
    }

    public void setSeverity(Integer severity) {
        Severity = severity;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }
}
