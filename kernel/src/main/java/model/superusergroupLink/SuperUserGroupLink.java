package model.superusergroupLink;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperUserGroupLink")
public class SuperUserGroupLink extends BaseModel {
  
    /**
     *用户ID
     */
     private String userId;
    /**
     *用户组ID
     */
     private String groupId;
  
    public void setUserId(String userId){
      this.userId = userId;
    }
    public void setGroupId(String groupId){
      this.groupId = groupId;
    }
  
    public String getUserId(){
      return this.userId;
    }
  
    public String getGroupId(){
      return this.groupId;
    }


  
}
