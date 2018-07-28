package model.superdatasource;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperDatasource")
public class SuperDatasource extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *数据源类型
     */
     private String datasourceType;
    /**
     *数据库类型
     */
     private String dbType;
    /**
     *服务器IP
     */
     private String serverIp;
    /**
     *数据库名称
     */
     private String databaseName;
    /**
     *端口
     */
     private String portNum;
    /**
     *用户名
     */
     private String userName;
    /**
     *密码
     */
     private String password;
  
    public void setId(String id){
      this.id = id;
    }
    public void setDatasourceType(String datasourceType){
      this.datasourceType = datasourceType;
    }


    public void setDatabaseName(String databaseName){
      this.databaseName = databaseName;
    }
    public void setPortNum(String portNum){
      this.portNum = portNum;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }
    public void setPassword(String password){
      this.password = password;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getDatasourceType(){
      return this.datasourceType;
    }


    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getDatabaseName(){
      return this.databaseName;
    }
  
    public String getPortNum(){
      return this.portNum;
    }
  
    public String getUserName(){
      return this.userName;
    }
  
    public String getPassword(){
      return this.password;
    }
  
}
