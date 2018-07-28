package model.agentinfo;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("AgentInfo")
public class AgentInfo extends BaseModel {
  
    /**
     *主键
     */
     private String id;
    /**
     *代理名称
     */
     private String agentName;
    /**
     *代理类型
     */
     private String agentType;
    /**
     *代理缩写
     */
     private String agentAb;
    /**
     *代理版本
     */
     private String agentVersion;
    /**
     *创建人
     */
     private String founder;
    /**
     *代理路径
     */
     private String agentRoute;
    /**
     *创建时间
     */
     private Long createTime;
     /**
     * echarts
     */
     private String name;
     private String value;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setId(String id){
      this.id = id;
    }
    public void setAgentName(String agentName){
      this.agentName = agentName;
    }
    public void setAgentType(String agentType){
      this.agentType = agentType;
    }
    public void setAgentAb(String agentAb){
      this.agentAb = agentAb;
    }
    public void setAgentVersion(String agentVersion){
      this.agentVersion = agentVersion;
    }
    public void setFounder(String founder){
      this.founder = founder;
    }
    public void setAgentRoute(String agentRoute){
      this.agentRoute = agentRoute;
    }

  
    public String getId(){
      return this.id;
    }
  
    public String getAgentName(){
      return this.agentName;
    }
  
    public String getAgentType(){
      return this.agentType;
    }
  
    public String getAgentAb(){
      return this.agentAb;
    }
  
    public String getAgentVersion(){
      return this.agentVersion;
    }
  
    public String getFounder(){
      return this.founder;
    }
  
    public String getAgentRoute(){
      return this.agentRoute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
