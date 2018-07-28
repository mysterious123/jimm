package service.superseverity.impl;

import dao.BaseMapper;
import model.superseverity.SuperSeverity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superseverity.SuperSeverityService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperSeverityService")
public class SuperSeverityServiceImpl implements SuperSeverityService{
    private final Logger L = LoggerFactory.getLogger(SuperSeverityServiceImpl.class);
    private static final String NAMESPACE = SuperSeverity.class.getName();

    @Resource
    private BaseMapper<SuperSeverity> baseMapper;

    @Override
    public int insert(SuperSeverity superSeverity) {
        superSeverity.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superSeverity);
    }
    
    @Override
    public int delete(SuperSeverity superSeverity) {
        superSeverity.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superSeverity);
    }

    @Override
    public int update(SuperSeverity superSeverity) {
        superSeverity.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superSeverity);
    }

    @Override
    public List<SuperSeverity> selectList(SuperSeverity superSeverity) {
        superSeverity.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superSeverity);
    }

    @Override
    public SuperSeverity selectOne(SuperSeverity superSeverity) {
        superSeverity.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superSeverity);
    }
}
