package service.operationpolicydeployment;

import model.operationpolicydeployment.OperationPolicyDeployment;
import model.serverinfo.ServerInfo;

import java.util.List;

public interface OperationPolicyDeploymentService {
    /**
     * 新增
     * @param operationPolicyDeployment
     * @return
     */
    int insert(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 删除
     * @param operationPolicyDeployment
     * @return
     */
    int delete(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 更新
     * @param operationPolicyDeployment
     * @return
     */
    int update(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 获取所有
     * @param operationPolicyDeployment
     * @return
     */
    List<OperationPolicyDeployment> selectList(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 获取单个
     * @param operationPolicyDeployment
     * @return
     */
    OperationPolicyDeployment selectOne(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 查询已经关联的serverInfo
     * @param operationPolicyDeployment
     * @return
     */
    List<ServerInfo>  querySelectServerInfo(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 查询已经未关联的serverInfo
     * @param operationPolicyDeployment
     * @return
     */
    List<ServerInfo>  queryUnSelectServerInfo(OperationPolicyDeployment operationPolicyDeployment);

    /**
     * 批量插入关联的serverInfo
     * @param operationPolicyDeployment
     * @return
     */
    int batchInsert(OperationPolicyDeployment operationPolicyDeployment);
}
