package model.policycustom;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("PolicyCustom")
public class PolicyCustom extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *对象类型
     */
     private String objectType;
    /**
     *关键字
     */
     private String keyName;
    /**
     *指标名称
     */
     private String indexName;
    /**
     *类型
     */
     private String indexType;
    /**
     *属性
     */
     private String nature;
    /**
     *创建人
     */
     private String createPerson;
    /**
     *阀值A
     */
     private String thresholdA;
    /**
     *阀值B
     */
     private String thresholdB;
    /**
     *级别A
     */
     private String levelA;
    /**
     *级别B
     */
     private String levelB;
  
    public void setId(String id){
      this.id = id;
    }
    public void setObjectType(String objectType){
      this.objectType = objectType;
    }
    public void setKeyName(String keyName){
      this.keyName = keyName;
    }
    public void setIndexName(String indexName){
      this.indexName = indexName;
    }
    public void setIndexType(String indexType){
      this.indexType = indexType;
    }
    public void setNature(String nature){
      this.nature = nature;
    }
    public void setCreatePerson(String createPerson){
      this.createPerson = createPerson;
    }
    public void setThresholdA(String thresholdA){
      this.thresholdA = thresholdA;
    }
    public void setThresholdB(String thresholdB){
      this.thresholdB = thresholdB;
    }
    public void setLevelA(String levelA){
      this.levelA = levelA;
    }
    public void setLevelB(String levelB){
      this.levelB = levelB;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getObjectType(){
      return this.objectType;
    }
  
    public String getKeyName(){
      return this.keyName;
    }
  
    public String getIndexName(){
      return this.indexName;
    }
  
    public String getIndexType(){
      return this.indexType;
    }
  
    public String getNature(){
      return this.nature;
    }
  
    public String getCreatePerson(){
      return this.createPerson;
    }
  
    public String getThresholdA(){
      return this.thresholdA;
    }
  
    public String getThresholdB(){
      return this.thresholdB;
    }
  
    public String getLevelA(){
      return this.levelA;
    }
  
    public String getLevelB(){
      return this.levelB;
    }
  
}
