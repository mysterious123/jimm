package service.superrole.impl;

import dao.BaseMapper;
import model.supermenu.SuperMenu;
import model.superpermission.SuperPermission;
import model.superrole.SuperRole;
import model.superrolemenu.SuperRoleMenu;
import model.superrolepermission.SuperRolePermission;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superrole.SuperRoleService;
import service.superrolemenu.SuperRoleMenuService;
import service.superrolepermission.SuperRolePermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperRoleService")
public class SuperRoleServiceImpl implements SuperRoleService{
    private final Logger L = LoggerFactory.getLogger(SuperRoleServiceImpl.class);
    private static final String NAMESPACE = SuperRole.class.getName();

    @Resource
    private BaseMapper<SuperRole> baseMapper;

    @Resource
    private SuperRoleMenuService superRoleMenuService;

    @Resource
    private SuperRolePermissionService superRolePermissionService;

    @Override
    public int insert(SuperRole superRole) {
        int result = 0;
        superRole.setStatmentId(NAMESPACE + ".insert");
        superRole.setCreateTime(DateTime.now().getMillis());
        result += baseMapper.insert(superRole);

        for(SuperMenu superMenu : superRole.getSuperMenus()){
            SuperRoleMenu superRoleMenu = new SuperRoleMenu();
            superRoleMenu.setMenuId(superMenu.getId());
            superRoleMenu.setRoleId(superRole.getId());
            result += superRoleMenuService.insert(superRoleMenu);
        }

        for(SuperPermission superPermission : superRole.getSuperPermissions()){
            SuperRolePermission superRolePermission = new SuperRolePermission();
            superRolePermission.setPermission(superPermission.getPermission());
            superRolePermission.setRoleId(superRole.getId());
            result += superRolePermissionService.insert(superRolePermission);
        }
        return result;
    }
    
    @Override
    public int delete(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".delete");
        SuperRoleMenu superRoleMenu = new SuperRoleMenu();
        superRoleMenu.setRoleId(superRole.getId());
        SuperRolePermission superRolePermission = new SuperRolePermission();
        superRolePermission.setRoleId(superRole.getId());

        superRoleMenuService.delete(superRoleMenu);
        superRolePermissionService.delete(superRolePermission);
        return baseMapper.delete(superRole);
    }

    @Override
    public int update(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".update");
        SuperRoleMenu superRoleMenu = new SuperRoleMenu();
        superRoleMenu.setRoleId(superRole.getId());
        superRoleMenuService.delete(superRoleMenu);
        for(SuperMenu superMenu : superRole.getSuperMenus()){
            superRoleMenu = new SuperRoleMenu();
            superRoleMenu.setMenuId(superMenu.getId());
            superRoleMenu.setRoleId(superRole.getId());
            superRoleMenuService.insert(superRoleMenu);
        }
        SuperRolePermission superRolePermission = new SuperRolePermission();
        superRolePermission.setRoleId(superRole.getId());
        superRolePermissionService.delete(superRolePermission);
        for(SuperPermission superPermission : superRole.getSuperPermissions()){
            superRolePermission = new SuperRolePermission();
            superRolePermission.setPermission(superPermission.getPermission());
            superRolePermission.setRoleId(superRole.getId());
            superRolePermissionService.insert(superRolePermission);
        }

        return baseMapper.update(superRole);
    }

    @Override
    public List<SuperRole> selectList(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superRole);
    }

    @Override
    public SuperRole selectOne(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superRole);
    }

    @Override
    public List<SuperMenu> selectMenuByRoleId(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".selectMenuByRoleId");
        return baseMapper.selectList(superRole,SuperMenu.class);
    }

    @Override
    public List<SuperPermission> selectPermissionByRoleId(SuperRole superRole) {
        superRole.setStatmentId(NAMESPACE + ".selectPermissionByRoleId");
        return baseMapper.selectList(superRole,SuperPermission.class);
    }
}
