package model.codeInfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("codeInfo")
public class codeInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *字典类别
     */
     private String type;
    /**
     *字典代码
     */
     private String code;
    /**
     *字典名称
     */
     private String name;
    /**
     *字典值
     */
     private String value;
    /**
     *创建时间
     */
     private Long createDate;
    /**
     *更新时间
     */
     private Long updateDate;

  
    public void setId(String id){
      this.id = id;
    }
    public void setType(String type){
      this.type = type;
    }
    public void setCode(String code){
      this.code = code;
    }
    public void setName(String name){
      this.name = name;
    }
    public void setValue(String value){
      this.value = value;
    }


  
    public String getId(){
      return this.id;
    }
  
    public String getType(){
      return this.type;
    }
  
    public String getCode(){
      return this.code;
    }
  
    public String getName(){
      return this.name;
    }
  
    public String getValue(){
      return this.value;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
