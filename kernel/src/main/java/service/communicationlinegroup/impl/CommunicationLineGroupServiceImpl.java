package service.communicationlinegroup.impl;

import dao.BaseMapper;
import model.communicationlinegroup.CommunicationLineGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.communicationlinegroup.CommunicationLineGroupService;

import javax.annotation.Resource;
import java.util.List;

@Service("communicationLineGroupService")
public class CommunicationLineGroupServiceImpl implements CommunicationLineGroupService {
    private final Logger L = LoggerFactory.getLogger(CommunicationLineGroupServiceImpl.class);
    private static final String NAMESPACE = CommunicationLineGroup.class.getName();

    @Resource
    private BaseMapper<CommunicationLineGroup> baseMapper;

    @Override
    public int insert(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(CommunicationLineGroup);
    }
    
    @Override
    public int delete(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(CommunicationLineGroup);
    }

    @Override
    public int update(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(CommunicationLineGroup);
    }

    @Override
    public List<CommunicationLineGroup> selectList(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(CommunicationLineGroup);
    }

    @Override
    public CommunicationLineGroup selectOne(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(CommunicationLineGroup);
    }
}
