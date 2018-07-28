package model.servergroup;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ServerGroup")
public class ServerGroup extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *服务器组名称
     */
     private String serverGroupName;
    /**
     *上级服务器组编号
     */
     private String parentId;
    /**
     *服务器组图标
     */
     private String iconcls;
  
    public void setId(String id){
      this.id = id;
    }
    public void setServerGroupName(String serverGroupName){
      this.serverGroupName = serverGroupName;
    }
    public void setParentId(String parentId){
      this.parentId = parentId;
    }
    public void setIconcls(String iconcls){
      this.iconcls = iconcls;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getServerGroupName(){
      return this.serverGroupName;
    }
  
    public String getParentId(){
      return this.parentId;
    }
  
    public String getIconcls(){
      return this.iconcls;
    }
  
}
