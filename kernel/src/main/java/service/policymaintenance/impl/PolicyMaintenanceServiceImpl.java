package service.policymaintenance.impl;

import dao.BaseMapper;
import model.policymaintenance.PolicyMaintenance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.policymaintenance.PolicyMaintenanceService;

import javax.annotation.Resource;
import java.util.List;

@Service("PolicyMaintenanceService")
public class PolicyMaintenanceServiceImpl implements PolicyMaintenanceService{
    private final Logger L = LoggerFactory.getLogger(PolicyMaintenanceServiceImpl.class);
    private static final String NAMESPACE = PolicyMaintenance.class.getName();

    @Resource
    private BaseMapper<PolicyMaintenance> baseMapper;

    @Override
    public int insert(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(policyMaintenance);
    }
    
    @Override
    public int delete(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(policyMaintenance);
    }

    @Override
    public int update(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(policyMaintenance);
    }

    @Override
    public List<PolicyMaintenance> selectList(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(policyMaintenance);
    }

    @Override
    public PolicyMaintenance selectOne(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(policyMaintenance);
    }
}
