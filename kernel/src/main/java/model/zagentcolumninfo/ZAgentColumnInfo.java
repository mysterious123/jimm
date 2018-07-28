package model.zagentcolumninfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ZAgentColumnInfo")
public class ZAgentColumnInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *AGENT编号
     */
     private String agentId;
    /**
     *字段名
     */
     private String columnName;
    /**
     *字段类型
     */
     private String columnType;
    /**
     *正则表达式
     */
     private String regularExpression;
    /**
     *字段说明
     */
     private String columnExplain;
  
    public void setId(String id){
      this.id = id;
    }
    public void setAgentId(String agentId){
      this.agentId = agentId;
    }
    public void setColumnName(String columnName){
      this.columnName = columnName;
    }
    public void setColumnType(String columnType){
      this.columnType = columnType;
    }
    public void setRegularExpression(String regularExpression){
      this.regularExpression = regularExpression;
    }
    public void setColumnExplain(String columnExplain){
      this.columnExplain = columnExplain;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getAgentId(){
      return this.agentId;
    }
  
    public String getColumnName(){
      return this.columnName;
    }
  
    public String getColumnType(){
      return this.columnType;
    }
  
    public String getRegularExpression(){
      return this.regularExpression;
    }
  
    public String getColumnExplain(){
      return this.columnExplain;
    }
  
}
