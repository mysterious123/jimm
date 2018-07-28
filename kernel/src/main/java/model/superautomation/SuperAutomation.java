package model.superautomation;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperAutomation")
public class SuperAutomation extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *应用名
     */
     private String appName;
    /**
     *程序名
     */
     private String programmeName;
    /**
     *数据源名
     */
     private String datasource;
    /**
     *状态
     */
     private String status;
  
    public void setId(String id){
      this.id = id;
    }
    public void setAppName(String appName){
      this.appName = appName;
    }
    public void setProgrammeName(String programmeName){
      this.programmeName = programmeName;
    }
    public void setDatasource(String datasource){
      this.datasource = datasource;
    }
    public void setStatus(String status){
      this.status = status;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getAppName(){
      return this.appName;
    }
  
    public String getProgrammeName(){
      return this.programmeName;
    }
  
    public String getDatasource(){
      return this.datasource;
    }
  
    public String getStatus(){
      return this.status;
    }
  
}
