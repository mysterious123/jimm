package model.superrolepermission;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperRolePermission")
public class SuperRolePermission extends BaseModel {
  
    /**
     *
     */
     private String roleId;
    /**
     *
     */
     private String permission;

     private String userName;
    public void setRoleId(String roleId){
      this.roleId = roleId;
    }
    public void setPermission(String permission){
      this.permission = permission;
    }
  
    public String getRoleId(){
      return this.roleId;
    }
  
    public String getPermission(){
      return this.permission;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
