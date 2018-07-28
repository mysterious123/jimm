package model.releaseinfo;

import model.releaselink.ReleaseLink;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("ReleaseInfo")
public class ReleaseInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *服务器说明
     */
     private String serverExplain;
    /**
     *服务器IP
     */
     private String serverIp;
    /**
     *服务器名
     */
     private String serverName;
    /**
     *用户名
     */
     private String userName;
    /**
     *密码
     */
     private String password;
    /**
     *生效脚本
     */
     private String effectScript;

    /**
     *对应关系
     */
    List<ReleaseLink> releaseLink;
  
    public void setId(String id){
      this.id = id;
    }
    public void setServerExplain(String serverExplain){
      this.serverExplain = serverExplain;
    }
    public void setServerIp(String serverIp){
      this.serverIp = serverIp;
    }
    public void setServerName(String serverName){
      this.serverName = serverName;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }
    public void setPassword(String password){
      this.password = password;
    }
    public void setEffectScript(String effectScript){
      this.effectScript = effectScript;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getServerExplain(){
      return this.serverExplain;
    }
  
    public String getServerIp(){
      return this.serverIp;
    }
  
    public String getServerName(){
      return this.serverName;
    }
  
    public String getUserName(){
      return this.userName;
    }
  
    public String getPassword(){
      return this.password;
    }
  
    public String getEffectScript(){
      return this.effectScript;
    }

    public List<ReleaseLink> getReleaseLink() {
        return releaseLink;
    }

    public void setReleaseLink(List<ReleaseLink> releaseLink) {
        this.releaseLink = releaseLink;
    }
}
