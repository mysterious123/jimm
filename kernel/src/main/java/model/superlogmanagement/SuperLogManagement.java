package model.superlogmanagement;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperLogManagement")
public class SuperLogManagement extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *用户名
     */
     private String userName;
    /**
     *开始日期
     */
     private Long createTime;
    /**
     *操作内容
     */
     private String operateContent;
    /**
     *操作结果
     */
     private String operateResult;

    private Long startTime;
    private Long endTime;
    private String content;
  
    public void setId(String id){
      this.id = id;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }
    public void setOperateContent(String operateContent){
      this.operateContent = operateContent;
    }
    public void setOperateResult(String operateResult){
      this.operateResult = operateResult;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getUserName(){
      return this.userName;
    }
  

  
    public String getOperateContent(){
      return this.operateContent;
    }
  
    public String getOperateResult(){
      return this.operateResult;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
