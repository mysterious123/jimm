package model.devicegroup;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("DeviceGroup")
public class DeviceGroup extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *网络设备组名称
     */
     private String networkDeviceGroupName;
    /**
     *上级网络设备
     */
     private String parentId;
    /**
     *网络设备组图标
     */
     private String iconcls;
  
    public void setId(String id){
      this.id = id;
    }
    public void setNetworkDeviceGroupName(String networkDeviceGroupName){
      this.networkDeviceGroupName = networkDeviceGroupName;
    }
    public void setParentId(String parentId){
      this.parentId = parentId;
    }
    public void setIconcls(String iconcls){
      this.iconcls = iconcls;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getNetworkDeviceGroupName(){
      return this.networkDeviceGroupName;
    }
  
    public String getParentId(){
      return this.parentId;
    }
  
    public String getIconcls(){
      return this.iconcls;
    }
  
}
