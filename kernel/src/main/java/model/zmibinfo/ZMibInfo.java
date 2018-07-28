package model.zmibinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ZMibInfo")
public class ZMibInfo extends BaseModel {
  
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
     *厂家品牌
     */
     private String venderBrand;
    /**
     *指标组
     */
     private String indexGroup;
    /**
     *计算公式
     */
     private String calculationFormula;
    /**
     *内容描述
     */
     private String contentDescribe;
    /**
     * 单位
     */
    private String indexUnit;
  
    public void setId(String id){
      this.id = id;
    }
    public void setIndexType(String indexType){
      this.indexType = indexType;
    }
    public void setIndexName(String indexName){
      this.indexName = indexName;
    }
    public void setVenderBrand(String venderBrand){
      this.venderBrand = venderBrand;
    }
    public void setIndexGroup(String indexGroup){
      this.indexGroup = indexGroup;
    }
    public void setCalculationFormula(String calculationFormula){
      this.calculationFormula = calculationFormula;
    }
    public void setContentDescribe(String contentDescribe){
      this.contentDescribe = contentDescribe;
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
  
    public String getVenderBrand(){
      return this.venderBrand;
    }
  
    public String getIndexGroup(){
      return this.indexGroup;
    }
  
    public String getCalculationFormula(){
      return this.calculationFormula;
    }
  
    public String getContentDescribe(){
      return this.contentDescribe;
    }

    public String getIndexUnit() {
        return indexUnit;
    }

    public void setIndexUnit(String indexUnit) {
        this.indexUnit = indexUnit;
    }
}
