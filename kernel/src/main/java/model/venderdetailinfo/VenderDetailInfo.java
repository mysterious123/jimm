package model.venderdetailinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("VenderDetailInfo")
public class VenderDetailInfo extends BaseModel {
  
    /**
     *主键
     */
     private Integer id;
    /**
     *设备类型
     */
     private String deviceType;
    /**
     *设备型号
     */
     private String deviceVersion;
    /**
     *OBJECTID
     */
     private String objectId;
    /**
     *厂家编号
     */
     private Integer venderId;

    public void setDeviceType(String deviceType){
      this.deviceType = deviceType;
    }
    public void setDeviceVersion(String deviceVersion){
      this.deviceVersion = deviceVersion;
    }
    public void setObjectId(String objectId){
      this.objectId = objectId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceType(){
      return this.deviceType;
    }
  
    public String getDeviceVersion(){
      return this.deviceVersion;
    }
  
    public String getObjectId(){
      return this.objectId;
    }

    public Integer getVenderId() {
        return venderId;
    }

    public void setVenderId(Integer venderId) {
        this.venderId = venderId;
    }
}
