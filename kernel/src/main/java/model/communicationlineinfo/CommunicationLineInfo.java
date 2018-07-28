package model.communicationlineinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("CommunicationLineInfo")
public class CommunicationLineInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *线路名称
     */
     private String lineName;
    /**
     *线路别名
     */
     private String lineOtherName;
    /**
     *线路类型
     */
     private String lineType;
    /**
     *带宽（MB）
     */
     private String bandwidth;
    /**
     *网络区域
     */
     private String networkZone;
    /**
     *管理部门
     */
     private String department;
    /**
     *状态属性
     */
     private String stat;
    /**
     *所属机构
     */
     private String attributeOrganization;
    /**
     *服务商
     */
     private String facilitator;
    /**
     *线路编号
     */
     private String lineId;
    /**
     *开通时间
     */
     private String openTime;
    /**
     *到期时间
     */
     private String duedate;
    /**
     *监控策略
     */
     private String monitorTactics;
    /**
     *时段策略
     */
     private String timeTactics;
    /**
     *本端设备管理IP
     */
     private String localDeviceManageIp;
    /**
     *本端端口名称
     */
     private String localPortIp;
    /**
     *本端设备端口IP
     */
     private String localDevicePortIp;
    /**
     *本端设备名称
     */
     private String localDeviceName;
    /**
     *对端设备管理IP
     */
     private String endDeviceManageIp;
    /**
     *对端端口名称
     */
     private String endPortName;
    /**
     *对端设备端口IP
     */
     private String endDevicePortIp;
    /**
     *对端设备名称
     */
     private String endDeviceName;
    /**
     *通讯线路组编号
     */
     private String groupId;

     List groupIds;
  
    public void setId(String id){
      this.id = id;
    }
    public void setLineName(String lineName){
      this.lineName = lineName;
    }
    public void setLineOtherName(String lineOtherName){
      this.lineOtherName = lineOtherName;
    }
    public void setLineType(String lineType){
      this.lineType = lineType;
    }
    public void setBandwidth(String bandwidth){
      this.bandwidth = bandwidth;
    }
    public void setNetworkZone(String networkZone){
      this.networkZone = networkZone;
    }
    public void setDepartment(String department){
      this.department = department;
    }
    public void setStat(String stat){
      this.stat = stat;
    }
    public void setAttributeOrganization(String attributeOrganization){
      this.attributeOrganization = attributeOrganization;
    }
    public void setFacilitator(String facilitator){
      this.facilitator = facilitator;
    }
    public void setLineId(String lineId){
      this.lineId = lineId;
    }
    public void setOpenTime(String openTime){
      this.openTime = openTime;
    }
    public void setDuedate(String duedate){
      this.duedate = duedate;
    }
    public void setMonitorTactics(String monitorTactics){
      this.monitorTactics = monitorTactics;
    }
    public void setTimeTactics(String timeTactics){
      this.timeTactics = timeTactics;
    }
    public void setLocalDeviceManageIp(String localDeviceManageIp){
      this.localDeviceManageIp = localDeviceManageIp;
    }
    public void setLocalPortIp(String localPortIp){
      this.localPortIp = localPortIp;
    }
    public void setLocalDevicePortIp(String localDevicePortIp){
      this.localDevicePortIp = localDevicePortIp;
    }
    public void setLocalDeviceName(String localDeviceName){
      this.localDeviceName = localDeviceName;
    }
    public void setEndDeviceManageIp(String endDeviceManageIp){
      this.endDeviceManageIp = endDeviceManageIp;
    }
    public void setEndPortName(String endPortName){
      this.endPortName = endPortName;
    }
    public void setEndDevicePortIp(String endDevicePortIp){
      this.endDevicePortIp = endDevicePortIp;
    }
    public void setEndDeviceName(String endDeviceName){
      this.endDeviceName = endDeviceName;
    }
    public void setGroupId(String groupId){
      this.groupId = groupId;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getLineName(){
      return this.lineName;
    }
  
    public String getLineOtherName(){
      return this.lineOtherName;
    }
  
    public String getLineType(){
      return this.lineType;
    }
  
    public String getBandwidth(){
      return this.bandwidth;
    }
  
    public String getNetworkZone(){
      return this.networkZone;
    }
  
    public String getDepartment(){
      return this.department;
    }
  
    public String getStat(){
      return this.stat;
    }
  
    public String getAttributeOrganization(){
      return this.attributeOrganization;
    }
  
    public String getFacilitator(){
      return this.facilitator;
    }
  
    public String getLineId(){
      return this.lineId;
    }
  
    public String getOpenTime(){
      return this.openTime;
    }
  
    public String getDuedate(){
      return this.duedate;
    }
  
    public String getMonitorTactics(){
      return this.monitorTactics;
    }
  
    public String getTimeTactics(){
      return this.timeTactics;
    }
  
    public String getLocalDeviceManageIp(){
      return this.localDeviceManageIp;
    }
  
    public String getLocalPortIp(){
      return this.localPortIp;
    }
  
    public String getLocalDevicePortIp(){
      return this.localDevicePortIp;
    }
  
    public String getLocalDeviceName(){
      return this.localDeviceName;
    }
  
    public String getEndDeviceManageIp(){
      return this.endDeviceManageIp;
    }
  
    public String getEndPortName(){
      return this.endPortName;
    }
  
    public String getEndDevicePortIp(){
      return this.endDevicePortIp;
    }
  
    public String getEndDeviceName(){
      return this.endDeviceName;
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
