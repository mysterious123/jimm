package model.releaselink;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ReleaseLink")
public class ReleaseLink extends BaseModel {
  
    /**
     *发布对象ID
     */
     private String releaseId;
    /**
     *服务器组ID
     */
     private String serverGroupId;
  
    public void setReleaseId(String releaseId){
      this.releaseId = releaseId;
    }
    public void setServerGroupId(String serverGroupId){
      this.serverGroupId = serverGroupId;
    }
  
    public String getReleaseId(){
      return this.releaseId;
    }
  
    public String getServerGroupId(){
      return this.serverGroupId;
    }
  
}
