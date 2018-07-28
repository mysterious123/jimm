package model.mcrelation;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("McRelation")
public class McRelation extends BaseModel {
  
    /**
     *主键
     */
     private String contactsId;
    /**
     *管理组编号
     */
     private String manageGroupInfoId;
  
    public void setContactsId(String contactsId){
      this.contactsId = contactsId;
    }
    public void setManageGroupInfoId(String manageGroupInfoId){
      this.manageGroupInfoId = manageGroupInfoId;
    }
  
    public String getContactsId(){
      return this.contactsId;
    }
  
    public String getManageGroupInfoId(){
      return this.manageGroupInfoId;
    }
  
}
