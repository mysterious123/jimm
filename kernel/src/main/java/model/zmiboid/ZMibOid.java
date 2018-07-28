package model.zmiboid;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("ZMibOid")
public class ZMibOid extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *MIB编号
     */
     private String mibId;
    /**
     *OID名称
     */
     private String oidName;
    /**
     *OID单位
     */
     private String oidUnit;
    /**
     *OID值
     */
     private String oidValue;
    /**
     *子类
     */
     private String smallType;
    /**
     *BPS
     */
     private String bps;
    /**
     *本端
     */
     private String local;
    /**
     *对端
     */
     private String endOn;
  
    public void setId(String id){
      this.id = id;
    }
    public void setMibId(String mibId){
      this.mibId = mibId;
    }
    public void setOidName(String oidName){
      this.oidName = oidName;
    }
    public void setOidUnit(String oidUnit){
      this.oidUnit = oidUnit;
    }
    public void setOidValue(String oidValue){
      this.oidValue = oidValue;
    }
    public void setSmallType(String smallType){
      this.smallType = smallType;
    }
    public void setBps(String bps){
      this.bps = bps;
    }
    public void setLocal(String local){
      this.local = local;
    }
    public void setEndOn(String endOn){
      this.endOn = endOn;
    }
  
    public String getId(){
      return this.id;
    }
  
    public String getMibId(){
      return this.mibId;
    }
  
    public String getOidName(){
      return this.oidName;
    }
  
    public String getOidUnit(){
      return this.oidUnit;
    }
  
    public String getOidValue(){
      return this.oidValue;
    }
  
    public String getSmallType(){
      return this.smallType;
    }
  
    public String getBps(){
      return this.bps;
    }
  
    public String getLocal(){
      return this.local;
    }
  
    public String getEndOn(){
      return this.endOn;
    }
  
}
