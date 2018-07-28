package model.communicationlinegroup;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("CommunicationLineGroup")
public class CommunicationLineGroup extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *通讯线路组名称
     */
     private String communicationLineGroupName;
    /**
     *上级通讯线路组号
     */
     private String parentId;
    /**
     *通讯线路组图标
     */
     private String iconcls;
  
    public void setId(String id){
      this.id = id;
    }
    public void setCommunicationLineGroupName(String communicationLineGroupName){
      this.communicationLineGroupName = communicationLineGroupName;
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
  
    public String getCommunicationLineGroupName(){
      return this.communicationLineGroupName;
    }
  
    public String getParentId(){
      return this.parentId;
    }
  
    public String getIconcls(){
      return this.iconcls;
    }
  
}
