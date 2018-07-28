package service.policycustom.impl;

import dao.BaseMapper;
import model.policycustom.PolicyCustom;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.policycustom.PolicyCustomService;

import javax.annotation.Resource;
import java.util.List;

@Service("PolicyCustomService")
public class PolicyCustomServiceImpl implements PolicyCustomService{
    private final Logger L = LoggerFactory.getLogger(PolicyCustomServiceImpl.class);
    private static final String NAMESPACE = PolicyCustom.class.getName();

    @Resource
    private BaseMapper<PolicyCustom> baseMapper;

    @Override
    public int insert(PolicyCustom policyCustom) {
        policyCustom.setStatmentId(NAMESPACE + ".insert");
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        policyCustom.setCreatePerson(user.getUserName());
        return baseMapper.insert(policyCustom);
    }
    
    @Override
    public int delete(PolicyCustom policyCustom) {
        policyCustom.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(policyCustom);
    }

    @Override
    public int update(PolicyCustom policyCustom) {
        policyCustom.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(policyCustom);
    }

    @Override
    public List<PolicyCustom> selectList(PolicyCustom policyCustom) {
        policyCustom.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(policyCustom);
    }

    @Override
    public PolicyCustom selectOne(PolicyCustom policyCustom) {
        policyCustom.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(policyCustom);
    }
}
