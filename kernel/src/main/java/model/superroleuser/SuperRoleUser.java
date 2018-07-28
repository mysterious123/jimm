package model.superroleuser;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperRoleUser")
public class SuperRoleUser extends BaseModel {
  
    /**
     *
     */
     private String roleId;
    /**
     *
     */
     private String userId;
  
    public void setRoleId(String roleId){
      this.roleId = roleId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
  
    public String getRoleId(){
      return this.roleId;
    }
  
    public String getUserId(){
      return this.userId;
    }
  
}
