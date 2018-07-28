package model.portgroup;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("PortGroup")
public class PortGroup extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *端口组名称
     */
     private String portGroupName;
    /**
     *上级端口组号
     */
     private String parentId;
    /**
     *端口组图标
     */
     private String iconcls;
  
    public void setId(String id){
      this.id = id;
    }
    public void setPortGroupName(String portGroupName){
      this.portGroupName = portGroupName;
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
  
    public String getPortGroupName(){
      return this.portGroupName;
    }
  
    public String getParentId(){
      return this.parentId;
    }
  
    public String getIconcls(){
      return this.iconcls;
    }
  
}
