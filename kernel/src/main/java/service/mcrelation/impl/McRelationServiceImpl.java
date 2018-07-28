package service.mcrelation.impl;

import dao.BaseMapper;
import model.mcrelation.McRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.mcrelation.McRelationService;

import javax.annotation.Resource;
import java.util.List;

@Service("McRelationService")
public class McRelationServiceImpl implements McRelationService{
    private final Logger L = LoggerFactory.getLogger(McRelationServiceImpl.class);
    private static final String NAMESPACE = McRelation.class.getName();

    @Resource
    private BaseMapper<McRelation> baseMapper;

    @Override
    public int insert(McRelation mcRelation) {
        mcRelation.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(mcRelation);
    }
    
    @Override
    public int delete(McRelation mcRelation) {
        mcRelation.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(mcRelation);
    }

    @Override
    public int update(McRelation mcRelation) {
        mcRelation.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(mcRelation);
    }

    @Override
    public List<McRelation> selectList(McRelation mcRelation) {
        mcRelation.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(mcRelation);
    }

    @Override
    public McRelation selectOne(McRelation mcRelation) {
        mcRelation.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(mcRelation);
    }
}
