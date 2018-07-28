package model.superrolemenu;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperRoleMenu")
public class SuperRoleMenu extends BaseModel {
  
    /**
     *
     */
     private String roleId;
    /**
     *
     */
     private String menuId;
  
    public void setRoleId(String roleId){
      this.roleId = roleId;
    }
    public void setMenuId(String menuId){
      this.menuId = menuId;
    }
  
    public String getRoleId(){
      return this.roleId;
    }
  
    public String getMenuId(){
      return this.menuId;
    }
  
}
