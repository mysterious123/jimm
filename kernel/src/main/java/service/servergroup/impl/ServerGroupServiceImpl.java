package service.servergroup.impl;

import dao.BaseMapper;
import model.servergroup.ServerGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.servergroup.ServerGroupService;

import javax.annotation.Resource;
import java.util.List;

@Service("ServerGroupService")
public class ServerGroupServiceImpl implements ServerGroupService{
    private final Logger L = LoggerFactory.getLogger(ServerGroupServiceImpl.class);
    private static final String NAMESPACE = ServerGroup.class.getName();

    @Resource
    private BaseMapper<ServerGroup> baseMapper;

    @Override
    public int insert(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(serverGroup);
    }
    
    @Override
    public int delete(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(serverGroup);
    }

    @Override
    public int update(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(serverGroup);
    }

    @Override
    public List<ServerGroup> selectList(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(serverGroup);
    }

    @Override
    public ServerGroup selectOne(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(serverGroup);
    }

    @Override
    public List<ServerGroup> selectLevel(ServerGroup serverGroup) {
        serverGroup.setStatmentId(NAMESPACE + ".selectLevel");
        return baseMapper.selectList(serverGroup);
    }
}
