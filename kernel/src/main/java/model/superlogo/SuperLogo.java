package model.superlogo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperLogo")
public class SuperLogo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *企业名称
     */
     private String name;
    /**
     *企业简称
     */
     private String shortName;
    /**
     *企业logo路径
     */
     private String logoRoute;
  
    public void setId(String id){
      this.id = id;
    }
    public void setName(String name){
      this.name = name;
    }
    public void setShortName(String shortName){
      this.shortName = shortName;
    }
    public void setLogoRoute(String logoRoute){
      this.logoRoute = logoRoute;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getName(){
      return this.name;
    }
  
    public String getShortName(){
      return this.shortName;
    }
  
    public String getLogoRoute(){
      return this.logoRoute;
    }
  
}
