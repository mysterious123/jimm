package model.zagentinfo;

import model.zagentcolumninfo.ZAgentColumnInfo;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("ZAgentInfo")
public class ZAgentInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *指标名称
     */
     private String indexName;
    /**
     *指标简写
     */
     private String indexShorthand;
    /**
     *监控代理
     */
     private String monitorAgent;
    /**
     *单位
     */
     private String unit;
    /**
     *关键字
     */
     private String keyWord;
    /**
     *计算公式
     */
     private String calculationFormula;
    /**
     *内容描述
     */
     private String contentDescribe;

     //字段集合
     private List<ZAgentColumnInfo> columnInfoList;

    public List<ZAgentColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ZAgentColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

    public void setId(String id){
      this.id = id;
    }
    public void setIndexName(String indexName){
      this.indexName = indexName;
    }
    public void setIndexShorthand(String indexShorthand){
      this.indexShorthand = indexShorthand;
    }
    public void setMonitorAgent(String monitorAgent){
      this.monitorAgent = monitorAgent;
    }
    public void setUnit(String unit){
      this.unit = unit;
    }
    public void setKeyWord(String keyWord){
      this.keyWord = keyWord;
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
  
    public String getIndexName(){
      return this.indexName;
    }
  
    public String getIndexShorthand(){
      return this.indexShorthand;
    }
  
    public String getMonitorAgent(){
      return this.monitorAgent;
    }
  
    public String getUnit(){
      return this.unit;
    }
  
    public String getKeyWord(){
      return this.keyWord;
    }
  
    public String getCalculationFormula(){
      return this.calculationFormula;
    }
  
    public String getContentDescribe(){
      return this.contentDescribe;
    }
  
}
