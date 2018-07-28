package service.superautomation.impl;

import dao.BaseMapper;
import model.superautomation.SuperAutomation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superautomation.SuperAutomationService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperAutomationService")
public class SuperAutomationServiceImpl implements SuperAutomationService{
    private final Logger L = LoggerFactory.getLogger(SuperAutomationServiceImpl.class);
    private static final String NAMESPACE = SuperAutomation.class.getName();

    @Resource
    private BaseMapper<SuperAutomation> baseMapper;

    @Override
    public int insert(SuperAutomation superAutomation) {
        superAutomation.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superAutomation);
    }
    
    @Override
    public int delete(SuperAutomation superAutomation) {
        superAutomation.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superAutomation);
    }

    @Override
    public int update(SuperAutomation superAutomation) {
        superAutomation.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superAutomation);
    }

    @Override
    public List<SuperAutomation> selectList(SuperAutomation superAutomation) {
        superAutomation.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superAutomation);
    }

    @Override
    public SuperAutomation selectOne(SuperAutomation superAutomation) {
        superAutomation.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superAutomation);
    }
}
