package model.jimdata.data;

import model.BaseModel;
import org.apache.ibatis.type.Alias;

import java.text.SimpleDateFormat;
import java.util.Date;

@Alias("JimData")
public class JimData extends BaseModel {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    public JimData() {

        this.dbName = "data"+sdf.format(new Date());
    }

    private String dbName;
    /**
     *
     */
     private String time;
    /**
     *
     */
     private String indexName;
    /**
     *
     */
     private String indexValue;
  
    public void setTime(String time){
      this.time = time;
    }
    public void setIndexName(String indexName){
      this.indexName = indexName;
    }
    public void setIndexValue(String indexValue){
      this.indexValue = indexValue;
    }
  
    public String getTime(){
      return this.time;
    }
  
    public String getIndexName(){
      return this.indexName;
    }
  
    public String getIndexValue(){
      return this.indexValue;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
