package model.userinfo;

import model.managegroupinfo.ManageGroupInfo;
import model.mcrelation.McRelation;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("UserInfo")
public class UserInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *用户名
     */
     private String userName;
    /**
     *密码
     */
     private String password;
    /**
     *角色
     */
     private String showName;
    /**
     *是否激活
     */
     private String active;
    /**
     *状态
     */
     private String stat;
    /**
     *手机号码
     */
     private String phone;
    /**
     *邮箱号码
     */
     private String email;
    /**
     *创建日期
     */
     private Long createDate;
    /**
     *
     */
     private List<ManageGroupInfo> manageGroups;

  
    public void setId(String id){
      this.id = id;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }
    public void setPassword(String password){
      this.password = password;
    }
    public void setShowName(String showName){
      this.showName = showName;
    }
    public void setActive(String active){
      this.active = active;
    }
    public void setStat(String stat){
      this.stat = stat;
    }
    public void setPhone(String phone){
      this.phone = phone;
    }
    public void setEmail(String email){
      this.email = email;
    }

    public String getId(){
      return this.id;
    }
  
    public String getUserName(){
      return this.userName;
    }
  
    public String getPassword(){
      return this.password;
    }
  
    public String getShowName(){
      return this.showName;
    }
  
    public String getActive(){
      return this.active;
    }
  
    public String getStat(){
      return this.stat;
    }
  
    public String getPhone(){
      return this.phone;
    }
  
    public String getEmail(){
      return this.email;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public List<ManageGroupInfo> getManageGroups() {
        return manageGroups;
    }

    public void setManageGroups(List<ManageGroupInfo> manageGroups) {
        this.manageGroups = manageGroups;
    }
}
