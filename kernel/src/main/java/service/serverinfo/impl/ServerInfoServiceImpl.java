package service.serverinfo.impl;

import dao.BaseMapper;
import model.serverinfo.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.serverinfo.ServerInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("serverInfoService")
public class ServerInfoServiceImpl implements ServerInfoService{
    private final Logger L = LoggerFactory.getLogger(ServerInfoServiceImpl.class);
    private static final String NAMESPACE = ServerInfo.class.getName();

    @Resource
    private BaseMapper<ServerInfo> baseMapper;

    @Override
    public int insert(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(serverInfo);
    }
    
    @Override
    public int delete(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(serverInfo);
    }

    @Override
    public int update(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(serverInfo);
    }

    @Override
    public List<ServerInfo> selectList(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(serverInfo);
    }

    @Override
    public ServerInfo selectOne(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(serverInfo);
    }

    @Override
    public int checkIpExist(ServerInfo serverInfo) {
        String ips[] = serverInfo.getServerIp().split(",");
        int flag = 0;
        for(String ip: ips){
            ServerInfo server = new ServerInfo();
            server.setStatmentId(NAMESPACE + ".checkIpExist");
            server.setPaging(false);
            server.setServerIp(ip);
            List<ServerInfo> serverInfoList = baseMapper.selectList(server);
            if(serverInfoList != null && serverInfoList.size() > 0){
                flag = 1;
            }
        }
        return flag;
    }

    @Override
    public List<ServerInfo> getServerByGroupId(ServerInfo serverInfo) {
        ServerInfo server = new ServerInfo();
        if(serverInfo.getGroupId() != null && serverInfo.getGroupId().contains(",")){
            server.setStatmentId(NAMESPACE + ".getServerByGroupId");
            server.setPaging(false);
            String groupIds[] = serverInfo.getGroupId().substring(0,serverInfo.getGroupId().length()-1).split(",");
            List groupIdList = new ArrayList();
            for(String groupId : groupIds){
                groupIdList.add(groupId);
            }
            server.setGroupIds(groupIdList);
            return baseMapper.selectList(server);
        }else{
            serverInfo.setStatmentId(NAMESPACE + ".selectList");
            return baseMapper.selectList(serverInfo);
        }

    }

    @Override
    public List<ServerInfo> getPolicyInfo(ServerInfo serverInfo) {
        serverInfo.setStatmentId(NAMESPACE + ".getPolicyInfo");
        return baseMapper.selectList(serverInfo);
    }
}
