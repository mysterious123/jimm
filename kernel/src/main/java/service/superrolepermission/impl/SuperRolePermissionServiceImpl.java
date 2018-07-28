package service.superrolepermission.impl;

import dao.BaseMapper;
import model.superrolepermission.SuperRolePermission;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superrolepermission.SuperRolePermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperRolePermissionService")
public class SuperRolePermissionServiceImpl implements SuperRolePermissionService{
    private final Logger L = LoggerFactory.getLogger(SuperRolePermissionServiceImpl.class);
    private static final String NAMESPACE = SuperRolePermission.class.getName();

    @Resource
    private BaseMapper<SuperRolePermission> baseMapper;
    @Override
    public int insert(SuperRolePermission superRolePermission) {
        superRolePermission.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superRolePermission);
    }
    
    @Override
    public int delete(SuperRolePermission superRolePermission) {
        superRolePermission.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superRolePermission);
    }

    @Override
    public int update(SuperRolePermission superRolePermission) {
        superRolePermission.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superRolePermission);
    }

    @Override
    public List<SuperRolePermission> selectList(SuperRolePermission superRolePermission) {
        superRolePermission.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superRolePermission);
    }

    @Override
    public SuperRolePermission selectOne(SuperRolePermission superRolePermission) {
        superRolePermission.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superRolePermission);
    }

    @Override
    public List<SuperRolePermission> getPermissions(SuperRolePermission superRolePermission, String permission) {
        superRolePermission.setStatmentId(NAMESPACE + ".getPermissions");
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();
        superRolePermission.setPermission(permission);
        superRolePermission.setUserName(userName);
        return  baseMapper.selectList(superRolePermission);
    }
}
