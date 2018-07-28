package service.kafkadata.impl;


import dao.BaseMapper;
import model.kafkadata.HeartBeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.kafkadata.HeartBeatService;

import javax.annotation.Resource;
import java.util.List;

@Service("HeartBeatService")
public class HeartBeatServiceImpl implements HeartBeatService {
    private final Logger L = LoggerFactory.getLogger(HeartBeatServiceImpl.class);
    private static final String NAMESPACE = HeartBeat.class.getName();

    @Resource
    private BaseMapper<HeartBeat> baseMapper;

    @Override
    public int insert(HeartBeat heartBeat) {
        heartBeat.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(heartBeat);
    }
    
    @Override
    public int delete(HeartBeat heartBeat) {
        heartBeat.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(heartBeat);
    }

    @Override
    public int update(HeartBeat heartBeat) {
        heartBeat.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(heartBeat);
    }

    @Override
    public List<HeartBeat> selectList(HeartBeat heartBeat) {
        heartBeat.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(heartBeat);
    }

    @Override
    public HeartBeat selectOne(HeartBeat heartBeat) {
        heartBeat.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(heartBeat);
    }
}
