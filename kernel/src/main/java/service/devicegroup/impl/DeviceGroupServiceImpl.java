package service.devicegroup.impl;

import dao.BaseMapper;
import model.devicegroup.DeviceGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.devicegroup.DeviceGroupService;

import javax.annotation.Resource;
import java.util.List;

@Service("DeviceGroupService")
public class DeviceGroupServiceImpl implements DeviceGroupService{
    private final Logger L = LoggerFactory.getLogger(DeviceGroupServiceImpl.class);
    private static final String NAMESPACE = DeviceGroup.class.getName();

    @Resource
    private BaseMapper<DeviceGroup> baseMapper;

    @Override
    public int insert(DeviceGroup deviceGroup) {
        deviceGroup.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(deviceGroup);
    }
    
    @Override
    public int delete(DeviceGroup deviceGroup) {
        deviceGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(deviceGroup);
    }

    @Override
    public int update(DeviceGroup deviceGroup) {
        deviceGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(deviceGroup);
    }

    @Override
    public List<DeviceGroup> selectList(DeviceGroup deviceGroup) {
        deviceGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(deviceGroup);
    }

    @Override
    public DeviceGroup selectOne(DeviceGroup deviceGroup) {
        deviceGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(deviceGroup);
    }
}
