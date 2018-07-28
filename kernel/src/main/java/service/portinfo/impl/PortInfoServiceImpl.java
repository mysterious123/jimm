package service.portinfo.impl;

import dao.BaseMapper;
import model.deviceinfo.DeviceInfo;
import model.portinfo.PortInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.portinfo.PortInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PortInfoService")
public class PortInfoServiceImpl implements PortInfoService{
    private final Logger L = LoggerFactory.getLogger(PortInfoServiceImpl.class);
    private static final String NAMESPACE = PortInfo.class.getName();

    @Resource
    private BaseMapper<PortInfo> baseMapper;

    @Override
    public int insert(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(portInfo);
    }
    
    @Override
    public int delete(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(portInfo);
    }

    @Override
    public int update(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(portInfo);
    }

    @Override
    public List<PortInfo> selectList(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(portInfo);
    }

    @Override
    public PortInfo selectOne(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(portInfo);
    }

    @Override
    public int checkPortIpExist(PortInfo portInfo) {
        portInfo.setStatmentId(NAMESPACE + ".checkPortIpExist");
        PortInfo info = baseMapper.selectOne(portInfo);
        if(info == null){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public List<PortInfo> getPortByGroupId(PortInfo portInfo) {
        PortInfo port = new PortInfo();
        if(portInfo.getGroupId() != null && portInfo.getGroupId().contains(",")){
            port.setStatmentId(NAMESPACE + ".getPortByGroupId");
            String groupIds[] = portInfo.getGroupId().substring(0,portInfo.getGroupId().length()-1).split(",");
            List groupIdList = new ArrayList();
            for(String groupId : groupIds){
                groupIdList.add(groupId);
            }
            port.setPaging(false);
            port.setGroupIds(groupIdList);
            return baseMapper.selectList(port);
        }else{
            portInfo.setStatmentId(NAMESPACE + ".selectList");
            return baseMapper.selectList(portInfo);
        }
    }
}
