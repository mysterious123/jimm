package service.policyperiod.impl;

import dao.BaseMapper;
import model.policyperiod.PolicyPeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.policyperiod.PolicyPeriodService;

import javax.annotation.Resource;
import java.util.List;

@Service("PolicyPeriodService")
public class PolicyPeriodServiceImpl implements PolicyPeriodService{
    private final Logger L = LoggerFactory.getLogger(PolicyPeriodServiceImpl.class);
    private static final String NAMESPACE = PolicyPeriod.class.getName();

    @Resource
    private BaseMapper<PolicyPeriod> baseMapper;

    @Override
    public int insert(PolicyPeriod policyPeriod) {
        policyPeriod.setStatmentId(NAMESPACE + ".insert");
        policyPeriod.setCreateUser("0");
        return baseMapper.insert(policyPeriod);
    }
    
    @Override
    public int delete(PolicyPeriod policyPeriod) {
        policyPeriod.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(policyPeriod);
    }

    @Override
    public int update(PolicyPeriod policyPeriod) {
        policyPeriod.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(policyPeriod);
    }

    @Override
    public List<PolicyPeriod> selectList(PolicyPeriod policyPeriod) {
        policyPeriod.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(policyPeriod);
    }

    @Override
    public PolicyPeriod selectOne(PolicyPeriod policyPeriod) {
        policyPeriod.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(policyPeriod);
    }
}
