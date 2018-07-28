package model.operationpolicyparam;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("OperationPolicyParam")
public class OperationPolicyParam extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *指标类型
     */
     private String indexType;
    /**
     *指标名称
     */
     private String indexName;
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
    /**
     *采集间隔
     */
     private String collectSpace;
    /**
     *超时
     */
     private String overTime;
    /**
     *重试间隔
     */
     private String retrySpace;
    /**
     *重试次数
     */
     private String retryNum;
    /**
     *策略编号
     */
     private String policyId;
  
    public void setId(String id){
      this.id = id;
    }
    public void setIndexType(String indexType){
      this.indexType = indexType;
    }
    public void setIndexName(String indexName){
      this.indexName = indexName;
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
    public void setCollectSpace(String collectSpace){
      this.collectSpace = collectSpace;
    }
    public void setOverTime(String overTime){
      this.overTime = overTime;
    }
    public void setRetrySpace(String retrySpace){
      this.retrySpace = retrySpace;
    }
    public void setRetryNum(String retryNum){
      this.retryNum = retryNum;
    }
    public void setPolicyId(String policyId){
      this.policyId = policyId;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getIndexType(){
      return this.indexType;
    }
  
    public String getIndexName(){
      return this.indexName;
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
  
    public String getCollectSpace(){
      return this.collectSpace;
    }
  
    public String getOverTime(){
      return this.overTime;
    }
  
    public String getRetrySpace(){
      return this.retrySpace;
    }
  
    public String getRetryNum(){
      return this.retryNum;
    }
  
    public String getPolicyId(){
      return this.policyId;
    }
  
}
