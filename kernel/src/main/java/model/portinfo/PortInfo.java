package model.portinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;
import java.util.Map;

@Alias("PortInfo")
public class PortInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *重要端口标识，’1‘重要，’0‘不重要
     */
     private String importantStat;
    /**
     *设备管理IP
     */
     private String deviceManageIp;
    /**
     *端口名称
     */
     private String portName;
    /**
     *端口IP
     */
     private String portIp;
    /**
     *带宽（MB）
     */
     private String bandwidth;
    /**
     *对端设备管理IP 
     */
     private String endDeviceManageIp;
    /**
     *对端设备名称 
     */
     private String endDeviceName;
    /**
     *对端端口名称 
     */
     private String endPortName;
    /**
     *对端端口IP 
     */
     private String serverGroupId;
    /**
     *对端端口VLAN号
     */
     private String endPortVlan;
    /**
     *对端管理部门
     */
     private String endDepartment;
    /**
     *监控策略
     */
     private String monitorPolicy;
    /**
     *时段策略
     */
     private String timePolicy;
    /**
     *状态属性
     */
     private String stat;
    /**
     *重要端口用途
     */
     private String importantPortUse;
    /**
     *备注
     */
     private String remarks;
    /**
     *
     */
     private String userId;
    /**
     *
     */
     private String groupId;
    /**
     *
     */
     private String deviceId;
    /**
     *设备名称
     */
     private String deviceName;
    /**
     *端口VLAN
     */
     private String portVlan;
    /**
     *管理部门
     */
     private String department;

     private List groupIds;
  
    public void setId(String id){
      this.id = id;
    }
    public void setImportantStat(String importantStat){
      this.importantStat = importantStat;
    }
    public void setDeviceManageIp(String deviceManageIp){
      this.deviceManageIp = deviceManageIp;
    }
    public void setPortName(String portName){
      this.portName = portName;
    }
    public void setPortIp(String portIp){
      this.portIp = portIp;
    }
    public void setBandwidth(String bandwidth){
      this.bandwidth = bandwidth;
    }
    public void setEndDeviceManageIp(String endDeviceManageIp){
      this.endDeviceManageIp = endDeviceManageIp;
    }
    public void setEndDeviceName(String endDeviceName){
      this.endDeviceName = endDeviceName;
    }
    public void setEndPortName(String endPortName){
      this.endPortName = endPortName;
    }
    public void setServerGroupId(String serverGroupId){
      this.serverGroupId = serverGroupId;
    }
    public void setEndPortVlan(String endPortVlan){
      this.endPortVlan = endPortVlan;
    }
    public void setEndDepartment(String endDepartment){
      this.endDepartment = endDepartment;
    }
    public void setMonitorPolicy(String monitorPolicy){
      this.monitorPolicy = monitorPolicy;
    }
    public void setTimePolicy(String timePolicy){
      this.timePolicy = timePolicy;
    }
    public void setStat(String stat){
      this.stat = stat;
    }
    public void setImportantPortUse(String importantPortUse){
      this.importantPortUse = importantPortUse;
    }
    public void setRemarks(String remarks){
      this.remarks = remarks;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public void setGroupId(String groupId){
      this.groupId = groupId;
    }
    public void setDeviceId(String deviceId){
      this.deviceId = deviceId;
    }
    public void setDeviceName(String deviceName){
      this.deviceName = deviceName;
    }
    public void setPortVlan(String portVlan){
      this.portVlan = portVlan;
    }
    public void setDepartment(String department){
      this.department = department;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getImportantStat(){
      return this.importantStat;
    }
  
    public String getDeviceManageIp(){
      return this.deviceManageIp;
    }
  
    public String getPortName(){
      return this.portName;
    }
  
    public String getPortIp(){
      return this.portIp;
    }
  
    public String getBandwidth(){
      return this.bandwidth;
    }
  
    public String getEndDeviceManageIp(){
      return this.endDeviceManageIp;
    }
  
    public String getEndDeviceName(){
      return this.endDeviceName;
    }
  
    public String getEndPortName(){
      return this.endPortName;
    }
  
    public String getServerGroupId(){
      return this.serverGroupId;
    }
  
    public String getEndPortVlan(){
      return this.endPortVlan;
    }
  
    public String getEndDepartment(){
      return this.endDepartment;
    }
  
    public String getMonitorPolicy(){
      return this.monitorPolicy;
    }
  
    public String getTimePolicy(){
      return this.timePolicy;
    }
  
    public String getStat(){
      return this.stat;
    }
  
    public String getImportantPortUse(){
      return this.importantPortUse;
    }
  
    public String getRemarks(){
      return this.remarks;
    }
  
    public String getUserId(){
      return this.userId;
    }
  
    public String getGroupId(){
      return this.groupId;
    }
  
    public String getDeviceId(){
      return this.deviceId;
    }
  
    public String getDeviceName(){
      return this.deviceName;
    }
  
    public String getPortVlan(){
      return this.portVlan;
    }
  
    public String getDepartment(){
      return this.department;
    }

    public List getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List groupIds) {
        this.groupIds = groupIds;
    }
}
