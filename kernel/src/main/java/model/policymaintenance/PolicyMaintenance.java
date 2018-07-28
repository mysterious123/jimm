package model.policymaintenance;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("PolicyMaintenance")
public class PolicyMaintenance extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *维护期名称
     */
     private String maintenanceName;
    /**
     *开始日期
     */
     private String startDate;
    /**
     *结束日期
     */
     private String endDate;
    /**
     *开始时间
     */
    private String startTime;
    /**
     *结束时间
     */
    private String endTime;
    /**
     *备注
     */
     private String remark;
    /**
     *状态
     */
     private String status;
  
    public void setId(String id){
      this.id = id;
    }
    public void setMaintenanceName(String maintenanceName){
      this.maintenanceName = maintenanceName;
    }
    public void setEndDate(String endDate){
      this.endDate = endDate;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public void setStatus(String status){
      this.status = status;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getMaintenanceName(){
      return this.maintenanceName;
    }

  
    public String getEndDate(){
      return this.endDate;
    }
  
    public String getRemark(){
      return this.remark;
    }
  
    public String getStatus(){
      return this.status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
