package model.kafkadata;

import model.BaseModel;

/**
 * Created by Administrator on 2018/5/4.
 */
public class AlertStatus extends BaseModel {
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

    public String getDevDesc() {
        return DevDesc;
    }

    public void setDevDesc(String devDesc) {
        DevDesc = devDesc;
    }

    private  String DevDesc;

    public String getAPPLICATION_SYSTEM() {
        return APPLICATION_SYSTEM;
    }

    public void setAPPLICATION_SYSTEM(String APPLICATION_SYSTEM) {
        this.APPLICATION_SYSTEM = APPLICATION_SYSTEM;
    }

    public String getSERVER_NAME() {
        return SERVER_NAME;
    }

    public void setSERVER_NAME(String SERVER_NAME) {
        this.SERVER_NAME = SERVER_NAME;
    }

    public String getSTATE_NATURE() {
        return STATE_NATURE;
    }

    public void setSTATE_NATURE(String STATE_NATURE) {
        this.STATE_NATURE = STATE_NATURE;
    }

    public String getSERVER_USE() {
        return SERVER_USE;
    }

    public void setSERVER_USE(String SERVER_USE) {
        this.SERVER_USE = SERVER_USE;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getATTRIBUTE_ORGANIZATION() {
        return ATTRIBUTE_ORGANIZATION;
    }

    public void setATTRIBUTE_ORGANIZATION(String ATTRIBUTE_ORGANIZATION) {
        this.ATTRIBUTE_ORGANIZATION = ATTRIBUTE_ORGANIZATION;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getFIRM() {
        return FIRM;
    }

    public void setFIRM(String FIRM) {
        this.FIRM = FIRM;
    }

    public String getCOMPUTER_ROOM() {
        return COMPUTER_ROOM;
    }

    public void setCOMPUTER_ROOM(String COMPUTER_ROOM) {
        this.COMPUTER_ROOM = COMPUTER_ROOM;
    }

    public String getNETWORK_ZONE() {
        return NETWORK_ZONE;
    }

    public void setNETWORK_ZONE(String NETWORK_ZONE) {
        this.NETWORK_ZONE = NETWORK_ZONE;
    }

    public String getASSET_ID() {
        return ASSET_ID;
    }

    public void setASSET_ID(String ASSET_ID) {
        this.ASSET_ID = ASSET_ID;
    }

    public String getCOMPUTER_COUNTER() {
        return COMPUTER_COUNTER;
    }

    public void setCOMPUTER_COUNTER(String COMPUTER_COUNTER) {
        this.COMPUTER_COUNTER = COMPUTER_COUNTER;
    }

    public String getMAINTAINER() {
        return MAINTAINER;
    }

    public void setMAINTAINER(String MAINTAINER) {
        this.MAINTAINER = MAINTAINER;
    }

    public String getTIME_TACTICS() {
        return TIME_TACTICS;
    }

    public void setTIME_TACTICS(String TIME_TACTICS) {
        this.TIME_TACTICS = TIME_TACTICS;
    }

    public String getSERVER_TACTICS() {
        return SERVER_TACTICS;
    }

    public void setSERVER_TACTICS(String SERVER_TACTICS) {
        this.SERVER_TACTICS = SERVER_TACTICS;
    }

    public String getOS_TYPE() {
        return OS_TYPE;
    }

    public void setOS_TYPE(String OS_TYPE) {
        this.OS_TYPE = OS_TYPE;
    }

    public String getOS_DETAIL_VERSION() {
        return OS_DETAIL_VERSION;
    }

    public void setOS_DETAIL_VERSION(String OS_DETAIL_VERSION) {
        this.OS_DETAIL_VERSION = OS_DETAIL_VERSION;
    }

    public String getOS_BIT() {
        return OS_BIT;
    }

    public void setOS_BIT(String OS_BIT) {
        this.OS_BIT = OS_BIT;
    }

    public String getOS_PATCH_VERSION() {
        return OS_PATCH_VERSION;
    }

    public void setOS_PATCH_VERSION(String OS_PATCH_VERSION) {
        this.OS_PATCH_VERSION = OS_PATCH_VERSION;
    }

    public String getOS_CPU() {
        return OS_CPU;
    }

    public void setOS_CPU(String OS_CPU) {
        this.OS_CPU = OS_CPU;
    }

    public String getOS_MANAGE_GROUP() {
        return OS_MANAGE_GROUP;
    }

    public void setOS_MANAGE_GROUP(String OS_MANAGE_GROUP) {
        this.OS_MANAGE_GROUP = OS_MANAGE_GROUP;
    }

    public String getOS_MEMORY() {
        return OS_MEMORY;
    }

    public void setOS_MEMORY(String OS_MEMORY) {
        this.OS_MEMORY = OS_MEMORY;
    }

    public String getDB_TYPE() {
        return DB_TYPE;
    }

    public void setDB_TYPE(String DB_TYPE) {
        this.DB_TYPE = DB_TYPE;
    }

    public String getDB_BIT() {
        return DB_BIT;
    }

    public void setDB_BIT(String DB_BIT) {
        this.DB_BIT = DB_BIT;
    }

    public String getDB_MANAGE_GROUP() {
        return DB_MANAGE_GROUP;
    }

    public void setDB_MANAGE_GROUP(String DB_MANAGE_GROUP) {
        this.DB_MANAGE_GROUP = DB_MANAGE_GROUP;
    }

    public String getMW_TYPE() {
        return MW_TYPE;
    }

    public void setMW_TYPE(String MW_TYPE) {
        this.MW_TYPE = MW_TYPE;
    }

    public String getMW_VERSION() {
        return MW_VERSION;
    }

    public void setMW_VERSION(String MW_VERSION) {
        this.MW_VERSION = MW_VERSION;
    }

    public String getMW_BIT() {
        return MW_BIT;
    }

    public void setMW_BIT(String MW_BIT) {
        this.MW_BIT = MW_BIT;
    }

    public String getMW_MANAGE_GROUP() {
        return MW_MANAGE_GROUP;
    }

    public void setMW_MANAGE_GROUP(String MW_MANAGE_GROUP) {
        this.MW_MANAGE_GROUP = MW_MANAGE_GROUP;
    }

    private String APPLICATION_SYSTEM;
    private String  SERVER_NAME;
    private String   STATE_NATURE;
    private String   SERVER_USE;
    private String     DEPARTMENT;
    private String     ATTRIBUTE_ORGANIZATION;
    private String  VERSION;
    private String FIRM;
    private String   COMPUTER_ROOM;
    private String  NETWORK_ZONE;
    private String ASSET_ID;
    private String COMPUTER_COUNTER;
    private String MAINTAINER;
    private String TIME_TACTICS;
    private String SERVER_TACTICS;
    private String OS_TYPE;
    private String OS_DETAIL_VERSION;
    private String  OS_BIT;
    private String OS_PATCH_VERSION;
    private String  OS_CPU;
    private String OS_MANAGE_GROUP;
    private String OS_MEMORY;
    private String DB_TYPE;
    private String DB_BIT;
    private String DB_MANAGE_GROUP;
    private String MW_TYPE;
    private String MW_VERSION;
    private String  MW_BIT;
    private String  MW_MANAGE_GROUP;

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
