package model.managegroup;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ManageGroup")
public class ManageGroup extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *管理组名称
     */
     private String manageGroupName;
    /**
     *上级管理组编号
     */
     private String parentId;
  
    public void setId(String id){
      this.id = id;
    }
    public void setManageGroupName(String manageGroupName){
      this.manageGroupName = manageGroupName;
    }
    public void setParentId(String parentId){
      this.parentId = parentId;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getManageGroupName(){
      return this.manageGroupName;
    }
  
    public String getParentId(){
      return this.parentId;
    }
  
}
