package model.kafkadata;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("HeartBeat")
public class HeartBeat extends BaseModel {
  
    /**
     *主键
     */
     private String hbId;
    /**
     *毫秒数
     */
     private String hbClock;
    /**
     *
     */
     private String hbHost;
    /**
     *
     */
     private String hbVersion;
    /**
     *
     */
     private String hbTimestamp;
    /**
     *
     */
     private String hbTime;
    /**
     *
     */
     private String hbType;
    /**
     *agent uuid
     */
     private String hbAgentuuid;
    /**
     *错过心跳次数
     */
     private String hbMisfire;
    /**
     *创建时间
     */
     private String hbCreated;
    /**
     *更新时间
     */
     private String hbUpdated;
  
    public void setHbId(String hbId){
      this.hbId = hbId;
    }
    public void setHbClock(String hbClock){
      this.hbClock = hbClock;
    }
    public void setHbHost(String hbHost){
      this.hbHost = hbHost;
    }
    public void setHbVersion(String hbVersion){
      this.hbVersion = hbVersion;
    }
    public void setHbTimestamp(String hbTimestamp){
      this.hbTimestamp = hbTimestamp;
    }
    public void setHbTime(String hbTime){
      this.hbTime = hbTime;
    }
    public void setHbType(String hbType){
      this.hbType = hbType;
    }
    public void setHbAgentuuid(String hbAgentuuid){
      this.hbAgentuuid = hbAgentuuid;
    }
    public void setHbMisfire(String hbMisfire){
      this.hbMisfire = hbMisfire;
    }
    public void setHbCreated(String hbCreated){
      this.hbCreated = hbCreated;
    }
    public void setHbUpdated(String hbUpdated){
      this.hbUpdated = hbUpdated;
    }
  
    public String getHbId(){
      return this.hbId;
    }
  
    public String getHbClock(){
      return this.hbClock;
    }
  
    public String getHbHost(){
      return this.hbHost;
    }
  
    public String getHbVersion(){
      return this.hbVersion;
    }
  
    public String getHbTimestamp(){
      return this.hbTimestamp;
    }
  
    public String getHbTime(){
      return this.hbTime;
    }
  
    public String getHbType(){
      return this.hbType;
    }
  
    public String getHbAgentuuid(){
      return this.hbAgentuuid;
    }
  
    public String getHbMisfire(){
      return this.hbMisfire;
    }
  
    public String getHbCreated(){
      return this.hbCreated;
    }
  
    public String getHbUpdated(){
      return this.hbUpdated;
    }
  
}
