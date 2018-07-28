package model.operationpolicydeployment;

import model.serverinfo.ServerInfo;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("OperationPolicyDeployment")
public class OperationPolicyDeployment extends BaseModel {

    /**
     * 服务器ID
     */
    private String serverId;
    /**
     * 策略ID
     */
    private String policyId;

    private List<ServerInfo> serverInfos;

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getPolicyId() {
        return this.policyId;
    }

    public List<ServerInfo> getServerInfos() {
        return serverInfos;
    }

    public void setServerInfos(List<ServerInfo> serverInfos) {
        this.serverInfos = serverInfos;
    }
}
