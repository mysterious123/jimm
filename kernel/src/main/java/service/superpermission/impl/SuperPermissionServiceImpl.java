package service.superpermission.impl;

import dao.BaseMapper;
import model.superpermission.SuperPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superpermission.SuperPermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperPermissionService")
public class SuperPermissionServiceImpl implements SuperPermissionService{
    private final Logger L = LoggerFactory.getLogger(SuperPermissionServiceImpl.class);
    private static final String NAMESPACE = SuperPermission.class.getName();

    @Resource
    private BaseMapper<SuperPermission> baseMapper;

    @Override
    public int insert(SuperPermission superPermission) {
        superPermission.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superPermission);
    }
    
    @Override
    public int delete(SuperPermission superPermission) {
        superPermission.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superPermission);
    }

    @Override
    public int update(SuperPermission superPermission) {
        superPermission.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superPermission);
    }

    @Override
    public List<SuperPermission> selectList(SuperPermission superPermission) {
        superPermission.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superPermission);
    }

    @Override
    public SuperPermission selectOne(SuperPermission superPermission) {
        superPermission.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superPermission);
    }
}
