package service.portgroup.impl;

import dao.BaseMapper;
import model.portgroup.PortGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.portgroup.PortGroupService;

import javax.annotation.Resource;
import java.util.List;

@Service("PortGroupService")
public class PortGroupServiceImpl implements PortGroupService{
    private final Logger L = LoggerFactory.getLogger(PortGroupServiceImpl.class);
    private static final String NAMESPACE = PortGroup.class.getName();

    @Resource
    private BaseMapper<PortGroup> baseMapper;

    @Override
    public int insert(PortGroup portGroup) {
        portGroup.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(portGroup);
    }
    
    @Override
    public int delete(PortGroup portGroup) {
        portGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(portGroup);
    }

    @Override
    public int update(PortGroup portGroup) {
        portGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(portGroup);
    }

    @Override
    public List<PortGroup> selectList(PortGroup portGroup) {
        portGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(portGroup);
    }

    @Override
    public PortGroup selectOne(PortGroup portGroup) {
        portGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(portGroup);
    }
}
