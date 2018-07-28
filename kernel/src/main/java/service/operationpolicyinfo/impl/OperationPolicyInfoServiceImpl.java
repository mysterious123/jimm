package service.operationpolicyinfo.impl;

import dao.BaseMapper;
import model.operationpolicyinfo.OperationPolicyInfo;
import model.operationpolicyparam.OperationPolicyParam;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.operationpolicyinfo.OperationPolicyInfoService;
import service.operationpolicyparam.OperationPolicyParamService;

import javax.annotation.Resource;
import java.util.List;

@Service("OperationPolicyInfoService")
public class OperationPolicyInfoServiceImpl implements OperationPolicyInfoService{
    private final Logger L = LoggerFactory.getLogger(OperationPolicyInfoServiceImpl.class);
    private static final String NAMESPACE = OperationPolicyInfo.class.getName();

    @Resource
    private BaseMapper<OperationPolicyInfo> baseMapper;

    @Resource
    private OperationPolicyParamService operationPolicyParamService;

    @Override
    public int insert(OperationPolicyInfo operationPolicyInfo) {
        int i = 0;
        operationPolicyInfo.setStatmentId(NAMESPACE + ".insert");
        operationPolicyInfo.setCreateTime(DateTime.now().getMillis());
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        operationPolicyInfo.setCreatePerson(user.getUserName());
        i += baseMapper.insert(operationPolicyInfo);
        for(OperationPolicyParam operationPolicyParam : operationPolicyInfo.getOperationPolicyParams()){
            operationPolicyParam.setPolicyId(operationPolicyInfo.getId());
            operationPolicyParam.setId(null);
            i += operationPolicyParamService.insert(operationPolicyParam);
        }
        return i;
    }
    
    @Override
    public int delete(OperationPolicyInfo operationPolicyInfo) {
        //级联删除
        OperationPolicyParam operationPolicyParam = new OperationPolicyParam();
        operationPolicyParam.setPolicyId(operationPolicyInfo.getId());
        operationPolicyParamService.delete(operationPolicyParam);
        operationPolicyInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(operationPolicyInfo);
    }

    @Override
    public int update(OperationPolicyInfo operationPolicyInfo) {
        operationPolicyInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(operationPolicyInfo);
    }

    @Override
    public List<OperationPolicyInfo> selectList(OperationPolicyInfo operationPolicyInfo) {
        operationPolicyInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(operationPolicyInfo);
    }

    @Override
    public OperationPolicyInfo selectOne(OperationPolicyInfo operationPolicyInfo) {
        operationPolicyInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(operationPolicyInfo);
    }
}
