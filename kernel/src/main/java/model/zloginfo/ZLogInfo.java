package model.zloginfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ZLogInfo")
public class ZLogInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *事件类型
     */
     private String eventType;
    /**
     *事件子类型
     */
     private String eventSmallType;
    /**
     *厂家品牌
     */
     private String venderBrand;
    /**
     *事件组名
     */
     private String eventGroupName;
    /**
     *事件组号
     */
     private String eventGroupNum;
    /**
     *关键字
     */
     private String keyWord;
    /**
     *正则表达式
     */
     private String regularExpression;
    /**
     *内容描述
     */
     private String contentDescribe;
    /**
     *处理建议
     */
     private String handlePropose;
  
    public void setId(String id){
      this.id = id;
    }
    public void setEventType(String eventType){
      this.eventType = eventType;
    }
    public void setEventSmallType(String eventSmallType){
      this.eventSmallType = eventSmallType;
    }
    public void setVenderBrand(String venderBrand){
      this.venderBrand = venderBrand;
    }
    public void setEventGroupName(String eventGroupName){
      this.eventGroupName = eventGroupName;
    }
    public void setEventGroupNum(String eventGroupNum){
      this.eventGroupNum = eventGroupNum;
    }
    public void setKeyWord(String keyWord){
      this.keyWord = keyWord;
    }
    public void setRegularExpression(String regularExpression){
      this.regularExpression = regularExpression;
    }
    public void setContentDescribe(String contentDescribe){
      this.contentDescribe = contentDescribe;
    }
    public void setHandlePropose(String handlePropose){
      this.handlePropose = handlePropose;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getEventType(){
      return this.eventType;
    }
  
    public String getEventSmallType(){
      return this.eventSmallType;
    }
  
    public String getVenderBrand(){
      return this.venderBrand;
    }
  
    public String getEventGroupName(){
      return this.eventGroupName;
    }
  
    public String getEventGroupNum(){
      return this.eventGroupNum;
    }
  
    public String getKeyWord(){
      return this.keyWord;
    }
  
    public String getRegularExpression(){
      return this.regularExpression;
    }
  
    public String getContentDescribe(){
      return this.contentDescribe;
    }
  
    public String getHandlePropose(){
      return this.handlePropose;
    }
  
}
