package model.deviceinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("DeviceInfo")
public class DeviceInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *管理IP
     */
     private String manageIp;
    /**
     *设备名称
     */
     private String deviceName;
    /**
     *设备别名
     */
     private String deviceOtherName;
    /**
     *设备类型
     */
     private String deviceType;
    /**
     *设备型号
     */
     private String deviceVersion;
    /**
     *设备级别
     */
     private String deviceLevel;
    /**
     *SNMP版本
     */
     private String snmpVersion;
    /**
     *SNMP端口
     */
     private String snmpPort;
    /**
     *Community String RO
     */
     private String communityStringRo;
    /**
     *Community String RW
     */
     private String communityStringRw;
    /**
     *管理部门
     */
     private String department;
    /**
     *所属机构
     */
     private String attributeOrganization;
    /**
     *网络区域
     */
     private String networkZone;
    /**
     *状态属性
     */
     private String stat;
    /**
     *监控策略
     */
     private String monitorTactics;
    /**
     *时段策略
     */
     private String timeTactics;
    /**
     *备注
     */
     private String remarks;
    /**
     *厂商
     */
     private String firm;
    /**
     *ObjectID
     */
     private String objectId;
    /**
     *软件版本
     */
     private String softwareVersion;
    /**
     *供应商
     */
     private String supplier;
    /**
     *服务商
     */
     private String serviceProvider;
    /**
     *是否为网点
     */
     private String whetherDot;
    /**
     *资产编号
     */
     private String assetId;
    /**
     *上架时间
     */
     private String groundingTime;
    /**
     *机房
     */
     private String computerRoom;
    /**
     *机柜编号
     */
     private String computerCounter;
    /**
     *维保开始时间
     */
     private String maintenanceStartTime;
    /**
     *维保结束时间
     */
     private String maintenanceEndTime;
    /**
     *重要端口标识
     */
     private String importantPort;
    /**
     *端口状态
     */
     private String portStat;
    /**
     *端口名称
     */
     private String portName;
    /**
     *端口别名
     */
     private String portOtherName;
    /**
     *端口IP
     */
     private String portIp;
    /**
     *带宽（MB）
     */
     private String bandwidth;
    /**
     *用户编号
     */
     private String userId;
    /**
     *服务器编号
     */
     private String groupId;

     List groupIds;
  
    public void setId(String id){
      this.id = id;
    }
    public void setManageIp(String manageIp){
      this.manageIp = manageIp;
    }
    public void setDeviceName(String deviceName){
      this.deviceName = deviceName;
    }
    public void setDeviceOtherName(String deviceOtherName){
      this.deviceOtherName = deviceOtherName;
    }
    public void setDeviceType(String deviceType){
      this.deviceType = deviceType;
    }
    public void setDeviceVersion(String deviceVersion){
      this.deviceVersion = deviceVersion;
    }
    public void setDeviceLevel(String deviceLevel){
      this.deviceLevel = deviceLevel;
    }
    public void setSnmpVersion(String snmpVersion){
      this.snmpVersion = snmpVersion;
    }
    public void setSnmpPort(String snmpPort){
      this.snmpPort = snmpPort;
    }
    public void setCommunityStringRo(String communityStringRo){
      this.communityStringRo = communityStringRo;
    }
    public void setCommunityStringRw(String communityStringRw){
      this.communityStringRw = communityStringRw;
    }
    public void setDepartment(String department){
      this.department = department;
    }
    public void setAttributeOrganization(String attributeOrganization){
      this.attributeOrganization = attributeOrganization;
    }
    public void setNetworkZone(String networkZone){
      this.networkZone = networkZone;
    }
    public void setStat(String stat){
      this.stat = stat;
    }
    public void setMonitorTactics(String monitorTactics){
      this.monitorTactics = monitorTactics;
    }
    public void setTimeTactics(String timeTactics){
      this.timeTactics = timeTactics;
    }
    public void setRemarks(String remarks){
      this.remarks = remarks;
    }
    public void setFirm(String firm){
      this.firm = firm;
    }
    public void setObjectId(String objectId){
      this.objectId = objectId;
    }
    public void setSoftwareVersion(String softwareVersion){
      this.softwareVersion = softwareVersion;
    }
    public void setSupplier(String supplier){
      this.supplier = supplier;
    }
    public void setServiceProvider(String serviceProvider){
      this.serviceProvider = serviceProvider;
    }
    public void setWhetherDot(String whetherDot){
      this.whetherDot = whetherDot;
    }
    public void setAssetId(String assetId){
      this.assetId = assetId;
    }
    public void setGroundingTime(String groundingTime){
      this.groundingTime = groundingTime;
    }
    public void setComputerRoom(String computerRoom){
      this.computerRoom = computerRoom;
    }
    public void setComputerCounter(String computerCounter){
      this.computerCounter = computerCounter;
    }
    public void setMaintenanceStartTime(String maintenanceStartTime){
      this.maintenanceStartTime = maintenanceStartTime;
    }
    public void setMaintenanceEndTime(String maintenanceEndTime){
      this.maintenanceEndTime = maintenanceEndTime;
    }
    public void setImportantPort(String importantPort){
      this.importantPort = importantPort;
    }
    public void setPortStat(String portStat){
      this.portStat = portStat;
    }
    public void setPortName(String portName){
      this.portName = portName;
    }
    public void setPortOtherName(String portOtherName){
      this.portOtherName = portOtherName;
    }
    public void setPortIp(String portIp){
      this.portIp = portIp;
    }
    public void setBandwidth(String bandwidth){
      this.bandwidth = bandwidth;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public void setGroupId(String groupId){
      this.groupId = groupId;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getManageIp(){
      return this.manageIp;
    }
  
    public String getDeviceName(){
      return this.deviceName;
    }
  
    public String getDeviceOtherName(){
      return this.deviceOtherName;
    }
  
    public String getDeviceType(){
      return this.deviceType;
    }
  
    public String getDeviceVersion(){
      return this.deviceVersion;
    }
  
    public String getDeviceLevel(){
      return this.deviceLevel;
    }
  
    public String getSnmpVersion(){
      return this.snmpVersion;
    }
  
    public String getSnmpPort(){
      return this.snmpPort;
    }
  
    public String getCommunityStringRo(){
      return this.communityStringRo;
    }
  
    public String getCommunityStringRw(){
      return this.communityStringRw;
    }
  
    public String getDepartment(){
      return this.department;
    }
  
    public String getAttributeOrganization(){
      return this.attributeOrganization;
    }
  
    public String getNetworkZone(){
      return this.networkZone;
    }
  
    public String getStat(){
      return this.stat;
    }
  
    public String getMonitorTactics(){
      return this.monitorTactics;
    }
  
    public String getTimeTactics(){
      return this.timeTactics;
    }
  
    public String getRemarks(){
      return this.remarks;
    }
  
    public String getFirm(){
      return this.firm;
    }
  
    public String getObjectId(){
      return this.objectId;
    }
  
    public String getSoftwareVersion(){
      return this.softwareVersion;
    }
  
    public String getSupplier(){
      return this.supplier;
    }
  
    public String getServiceProvider(){
      return this.serviceProvider;
    }
  
    public String getWhetherDot(){
      return this.whetherDot;
    }
  
    public String getAssetId(){
      return this.assetId;
    }
  
    public String getGroundingTime(){
      return this.groundingTime;
    }
  
    public String getComputerRoom(){
      return this.computerRoom;
    }
  
    public String getComputerCounter(){
      return this.computerCounter;
    }
  
    public String getMaintenanceStartTime(){
      return this.maintenanceStartTime;
    }
  
    public String getMaintenanceEndTime(){
      return this.maintenanceEndTime;
    }
  
    public String getImportantPort(){
      return this.importantPort;
    }
  
    public String getPortStat(){
      return this.portStat;
    }
  
    public String getPortName(){
      return this.portName;
    }
  
    public String getPortOtherName(){
      return this.portOtherName;
    }
  
    public String getPortIp(){
      return this.portIp;
    }
  
    public String getBandwidth(){
      return this.bandwidth;
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
}
