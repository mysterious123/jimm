package model.serverinfo;

import model.operationpolicyparam.OperationPolicyParam;
import model.zagentinfo.ZAgentInfo;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("ServerInfo")
public class ServerInfo extends BaseModel {
  
    /**
     *主键
     */
     private String serverId;
    /**
     *基本信息--服务器IP
     */
     private String serverIp;
    /**
     *基本信息--服务器名称
     */
     private String serverName;
    /**
     *基本信息--应用系统
     */
     private String applicationSystem;
    /**
     *基本信息--服务器用途
     */
     private String serverUse;
    /**
     *基本信息--状态属性
     */
     private String stateNature;
    /**
     *基本信息--所属机构
     */
     private String attributeOrganization;
    /**
     *基本信息--管理部门
     */
     private String department;
    /**
     *基本信息--厂商
     */
     private String firm;
    /**
     *基本信息--型号
     */
     private String version;
    /**
     *基本信息--网络区域
     */
     private String networkZone;
    /**
     *基本信息--机房
     */
     private String computerRoom;
    /**
     *基本信息--机柜
     */
     private String computerCounter;
    /**
     *基本信息--资产编号
     */
     private String assetId;
    /**
     *基本信息--维护商
     */
     private String maintainer;
    /**
     *基本信息--维保时间
     */
     private String maintainTime;
    /**
     *基本信息--服务器策略
     */
     private String serverTactics;
    /**
     *基本信息--时段策略
     */
     private String timeTactics;
    /**
     *基本信息--备注
     */
     private String remarks;
    /**
     *软件信息--操作系统类型
     */
     private String osType;
    /**
     *软件信息--操作详细版本
     */
     private String osDetailVersion;
    /**
     *软件信息--操作系统位数
     */
     private String osBit;
    /**
     *软件信息--操作系统补丁版本
     */
     private String osPatchVersion;
    /**
     *软件信息--操作系统CPU
     */
     private String osCpu;
    /**
     *软件信息--操作系统管理组
     */
     private String osManageGroup;
    /**
     *软件信息--操作系统内存
     */
     private String osMemory;
    /**
     *软件信息--数据库类型
     */
     private String dbType;
    /**
     *软件信息--数据库位数
     */
     private String dbBit;
    /**
     *软件信息--数据库管理组
     */
     private String dbManageGroup;
    /**
     *软件信息--中间件类型
     */
     private String mwType;
    /**
     *软件信息--中间件版本
     */
     private String mwVersion;
    /**
     *软件信息--中间件位数
     */
     private String mwBit;
    /**
     *软件信息--中间件管理组
     */
     private String mwManageGroup;
    /**
     *用户编号
     */
     private String userId;
    /**
     *服务器组编号
     */
     private String groupId;

     List groupIds;

     private OperationPolicyParam PolicyParams;
     private ZAgentInfo ZAgentInfos;
  
    public void setServerId(String serverId){
      this.serverId = serverId;
    }
    public void setServerIp(String serverIp){
      this.serverIp = serverIp;
    }
    public void setServerName(String serverName){
      this.serverName = serverName;
    }
    public void setApplicationSystem(String applicationSystem){
      this.applicationSystem = applicationSystem;
    }
    public void setServerUse(String serverUse){
      this.serverUse = serverUse;
    }
    public void setStateNature(String stateNature){
      this.stateNature = stateNature;
    }
    public void setAttributeOrganization(String attributeOrganization){
      this.attributeOrganization = attributeOrganization;
    }
    public void setDepartment(String department){
      this.department = department;
    }
    public void setFirm(String firm){
      this.firm = firm;
    }
    public void setVersion(String version){
      this.version = version;
    }
    public void setNetworkZone(String networkZone){
      this.networkZone = networkZone;
    }
    public void setComputerRoom(String computerRoom){
      this.computerRoom = computerRoom;
    }
    public void setComputerCounter(String computerCounter){
      this.computerCounter = computerCounter;
    }
    public void setAssetId(String assetId){
      this.assetId = assetId;
    }
    public void setMaintainer(String maintainer){
      this.maintainer = maintainer;
    }
    public void setMaintainTime(String maintainTime){
      this.maintainTime = maintainTime;
    }
    public void setServerTactics(String serverTactics){
      this.serverTactics = serverTactics;
    }
    public void setTimeTactics(String timeTactics){
      this.timeTactics = timeTactics;
    }
    public void setRemarks(String remarks){
      this.remarks = remarks;
    }
    public void setOsType(String osType){
      this.osType = osType;
    }
    public void setOsDetailVersion(String osDetailVersion){
      this.osDetailVersion = osDetailVersion;
    }
    public void setOsBit(String osBit){
      this.osBit = osBit;
    }
    public void setOsPatchVersion(String osPatchVersion){
      this.osPatchVersion = osPatchVersion;
    }
    public void setOsCpu(String osCpu){
      this.osCpu = osCpu;
    }
    public void setOsManageGroup(String osManageGroup){
      this.osManageGroup = osManageGroup;
    }
    public void setOsMemory(String osMemory){
      this.osMemory = osMemory;
    }
    public void setDbType(String dbType){
      this.dbType = dbType;
    }
    public void setDbBit(String dbBit){
      this.dbBit = dbBit;
    }
    public void setDbManageGroup(String dbManageGroup){
      this.dbManageGroup = dbManageGroup;
    }
    public void setMwType(String mwType){
      this.mwType = mwType;
    }
    public void setMwVersion(String mwVersion){
      this.mwVersion = mwVersion;
    }
    public void setMwBit(String mwBit){
      this.mwBit = mwBit;
    }
    public void setMwManageGroup(String mwManageGroup){
      this.mwManageGroup = mwManageGroup;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public void setGroupId(String groupId){
      this.groupId = groupId;
    }
  
    public String getServerId(){
      return this.serverId;
    }
  
    public String getServerIp(){
      return this.serverIp;
    }
  
    public String getServerName(){
      return this.serverName;
    }
  
    public String getApplicationSystem(){
      return this.applicationSystem;
    }
  
    public String getServerUse(){
      return this.serverUse;
    }
  
    public String getStateNature(){
      return this.stateNature;
    }
  
    public String getAttributeOrganization(){
      return this.attributeOrganization;
    }
  
    public String getDepartment(){
      return this.department;
    }
  
    public String getFirm(){
      return this.firm;
    }
  
    public String getVersion(){
      return this.version;
    }
  
    public String getNetworkZone(){
      return this.networkZone;
    }
  
    public String getComputerRoom(){
      return this.computerRoom;
    }
  
    public String getComputerCounter(){
      return this.computerCounter;
    }
  
    public String getAssetId(){
      return this.assetId;
    }
  
    public String getMaintainer(){
      return this.maintainer;
    }
  
    public String getMaintainTime(){
      return this.maintainTime;
    }
  
    public String getServerTactics(){
      return this.serverTactics;
    }
  
    public String getTimeTactics(){
      return this.timeTactics;
    }
  
    public String getRemarks(){
      return this.remarks;
    }
  
    public String getOsType(){
      return this.osType;
    }
  
    public String getOsDetailVersion(){
      return this.osDetailVersion;
    }
  
    public String getOsBit(){
      return this.osBit;
    }
  
    public String getOsPatchVersion(){
      return this.osPatchVersion;
    }
  
    public String getOsCpu(){
      return this.osCpu;
    }
  
    public String getOsManageGroup(){
      return this.osManageGroup;
    }
  
    public String getOsMemory(){
      return this.osMemory;
    }
  
    public String getDbType(){
      return this.dbType;
    }
  
    public String getDbBit(){
      return this.dbBit;
    }
  
    public String getDbManageGroup(){
      return this.dbManageGroup;
    }
  
    public String getMwType(){
      return this.mwType;
    }
  
    public String getMwVersion(){
      return this.mwVersion;
    }
  
    public String getMwBit(){
      return this.mwBit;
    }
  
    public String getMwManageGroup(){
      return this.mwManageGroup;
    }
  
    public String getUserId(){
      return this.userId;
    }
  
    public String getGroupId(){
      return this.groupId;
    }

    public List getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List groupIds) {
        this.groupIds = groupIds;
    }

    public OperationPolicyParam getPolicyParams() {
        return PolicyParams;
    }

    public void setPolicyParams(OperationPolicyParam policyParams) {
        PolicyParams = policyParams;
    }

    public ZAgentInfo getZAgentInfos() {
        return ZAgentInfos;
    }

    public void setZAgentInfos(ZAgentInfo ZAgentInfos) {
        this.ZAgentInfos = ZAgentInfos;
    }
}

