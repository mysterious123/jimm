package model.superseverity;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperSeverity")
public class SuperSeverity extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *级别
     */
     private String level;
    /**
     *级别英文名
     */
     private String englishName;
    /**
     *级别中文名
     */
     private String chineseName;
    /**
     *级别颜色
     */
     private String levelColour;
  
    public void setId(String id){
      this.id = id;
    }
    public void setLevel(String level){
      this.level = level;
    }
    public void setEnglishName(String englishName){
      this.englishName = englishName;
    }
    public void setChineseName(String chineseName){
      this.chineseName = chineseName;
    }
    public void setLevelColour(String levelColour){
      this.levelColour = levelColour;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getLevel(){
      return this.level;
    }
  
    public String getEnglishName(){
      return this.englishName;
    }
  
    public String getChineseName(){
      return this.chineseName;
    }
  
    public String getLevelColour(){
      return this.levelColour;
    }
  
}
