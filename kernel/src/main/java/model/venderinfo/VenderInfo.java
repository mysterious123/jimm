package model.venderinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("VenderInfo")
public class VenderInfo extends BaseModel {
  
    /**
     *主键
     */
     private Integer id;
    /**
     *厂家名称
     */
     private String venderName;
    /**
     *logo路径
     */
     private String logoRoute;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVenderName(String venderName){
      this.venderName = venderName;
    }
    public void setLogoRoute(String logoRoute){
      this.logoRoute = logoRoute;
    }
  

  
    public String getVenderName(){
      return this.venderName;
    }
  
    public String getLogoRoute(){
      return this.logoRoute;
    }
  
}
