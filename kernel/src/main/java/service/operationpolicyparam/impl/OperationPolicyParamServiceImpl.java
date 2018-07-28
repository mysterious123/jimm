package service.operationpolicyparam.impl;

import dao.BaseMapper;
import model.operationpolicyparam.OperationPolicyParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.operationpolicyparam.OperationPolicyParamService;

import javax.annotation.Resource;
import java.util.List;

@Service("OperationPolicyParamService")
public class OperationPolicyParamServiceImpl implements OperationPolicyParamService{
    private final Logger L = LoggerFactory.getLogger(OperationPolicyParamServiceImpl.class);
    private static final String NAMESPACE = OperationPolicyParam.class.getName();

    @Resource
    private BaseMapper<OperationPolicyParam> baseMapper;

    @Override
    public int insert(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(operationPolicyParam);
    }
    
    @Override
    public int delete(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(operationPolicyParam);
    }

    @Override
    public int update(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(operationPolicyParam);
    }

    @Override
    public List<OperationPolicyParam> selectList(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(operationPolicyParam);
    }

    @Override
    public OperationPolicyParam selectOne(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(operationPolicyParam);
    }
}
