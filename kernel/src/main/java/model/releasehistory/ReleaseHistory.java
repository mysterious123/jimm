package model.releasehistory;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ReleaseHistory")
public class ReleaseHistory extends BaseModel {
  
    /**
     *主键
     */
     private Integer id;
    /**
     *生效人
     */
     private String releasePerson;
    /**
     *生效时间
     */
     private Long releaseTime;
    /**
     *生效结果
     */
     private String releaseResault;
  

    public void setReleasePerson(String releasePerson){
      this.releasePerson = releasePerson;
    }
    public void setReleaseResault(String releaseResault){
      this.releaseResault = releaseResault;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReleasePerson(){
      return this.releasePerson;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleaseResault() {
        return releaseResault;
    }
}
