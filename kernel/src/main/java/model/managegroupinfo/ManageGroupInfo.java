package model.managegroupinfo;

import model.mcrelation.McRelation;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("ManageGroupInfo")
public class ManageGroupInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *管理组名
     */
     private String manageGroupName;
    /**
     *组员数
     */
     private String memberNum;
    /**
     *创建时间
     */
     private Long createTime;
    /**
     *管理层级编号
     */
     private String manageId;
    /**
     *用户编号
     */
     private String userId;

     List<McRelation> mcRelations;
  
    public void setId(String id){
      this.id = id;
    }
    public void setManageGroupName(String manageGroupName){
      this.manageGroupName = manageGroupName;
    }
    public void setMemberNum(String memberNum){
      this.memberNum = memberNum;
    }
    public void setManageId(String manageId){
      this.manageId = manageId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getManageGroupName(){
      return this.manageGroupName;
    }
  
    public String getMemberNum(){
      return this.memberNum;
    }
  

    public String getManageId(){
      return this.manageId;
    }
  
    public String getUserId(){
      return this.userId;
    }

    public List<McRelation> getMcRelations() {
        return mcRelations;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setMcRelations(List<McRelation> mcRelations) {
        this.mcRelations = mcRelations;
    }
}
