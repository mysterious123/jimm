package service.operationpolicydeployment.impl;

import dao.BaseMapper;
import model.operationpolicydeployment.OperationPolicyDeployment;
import model.serverinfo.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.operationpolicydeployment.OperationPolicyDeploymentService;

import javax.annotation.Resource;
import java.util.List;

@Service("OperationPolicyDeploymentService")
public class OperationPolicyDeploymentServiceImpl implements OperationPolicyDeploymentService{
    private final Logger L = LoggerFactory.getLogger(OperationPolicyDeploymentServiceImpl.class);
    private static final String NAMESPACE = OperationPolicyDeployment.class.getName();

    @Resource
    private BaseMapper<OperationPolicyDeployment> baseMapper;

    @Override
    public int insert(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(operationPolicyDeployment);
    }
    
    @Override
    public int delete(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(operationPolicyDeployment);
    }

    @Override
    public int update(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(operationPolicyDeployment);
    }

    @Override
    public List<OperationPolicyDeployment> selectList(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(operationPolicyDeployment);
    }

    @Override
    public OperationPolicyDeployment selectOne(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(operationPolicyDeployment);
    }

    @Override
    public List<ServerInfo> querySelectServerInfo(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".querySelectServerInfo");
        return baseMapper.selectList(operationPolicyDeployment,ServerInfo.class);
    }

    @Override
    public List<ServerInfo> queryUnSelectServerInfo(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setStatmentId(NAMESPACE + ".queryUnSelectServerInfo");
        return baseMapper.selectList(operationPolicyDeployment,ServerInfo.class);
    }

    @Override
    public int batchInsert(OperationPolicyDeployment operationPolicyDeployment) {
        int i = 0;
        this.delete(operationPolicyDeployment);
        for(ServerInfo serverInfo : operationPolicyDeployment.getServerInfos()){
            OperationPolicyDeployment param = new OperationPolicyDeployment();
            param.setPolicyId(operationPolicyDeployment.getPolicyId());
            param.setServerId(serverInfo.getServerId());
            i += this.insert(param);
        }
        return i;
    }
}
