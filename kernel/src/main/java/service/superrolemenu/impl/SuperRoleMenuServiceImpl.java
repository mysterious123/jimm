package service.superrolemenu.impl;

import dao.BaseMapper;
import model.superrolemenu.SuperRoleMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superrolemenu.SuperRoleMenuService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperRoleMenuService")
public class SuperRoleMenuServiceImpl implements SuperRoleMenuService{
    private final Logger L = LoggerFactory.getLogger(SuperRoleMenuServiceImpl.class);
    private static final String NAMESPACE = SuperRoleMenu.class.getName();

    @Resource
    private BaseMapper<SuperRoleMenu> baseMapper;

    @Override
    public int insert(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superRoleMenu);
    }
    
    @Override
    public int delete(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superRoleMenu);
    }

    @Override
    public int update(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superRoleMenu);
    }

    @Override
    public List<SuperRoleMenu> selectList(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superRoleMenu);
    }

    @Override
    public SuperRoleMenu selectOne(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superRoleMenu);
    }
}
