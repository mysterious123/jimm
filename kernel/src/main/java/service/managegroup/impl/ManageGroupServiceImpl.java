package service.managegroup.impl;

import dao.BaseMapper;
import model.managegroup.ManageGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.managegroup.ManageGroupService;

import javax.annotation.Resource;
import java.util.List;

@Service("ManageGroupService")
public class ManageGroupServiceImpl implements ManageGroupService{
    private final Logger L = LoggerFactory.getLogger(ManageGroupServiceImpl.class);
    private static final String NAMESPACE = ManageGroup.class.getName();

    @Resource
    private BaseMapper<ManageGroup> baseMapper;

    @Override
    public int insert(ManageGroup manageGroup) {
        manageGroup.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(manageGroup);
    }
    
    @Override
    public int delete(ManageGroup manageGroup) {
        manageGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(manageGroup);
    }

    @Override
    public int update(ManageGroup manageGroup) {
        manageGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(manageGroup);
    }

    @Override
    public List<ManageGroup> selectList(ManageGroup manageGroup) {
        manageGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(manageGroup);
    }

    @Override
    public ManageGroup selectOne(ManageGroup manageGroup) {
        manageGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(manageGroup);
    }
}
