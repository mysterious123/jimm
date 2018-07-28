package service.supermenu.impl;

import dao.BaseMapper;
import model.supermenu.SuperMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.supermenu.SuperMenuService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperMenuService")
public class SuperMenuServiceImpl implements SuperMenuService{
    private final Logger L = LoggerFactory.getLogger(SuperMenuServiceImpl.class);
    private static final String NAMESPACE = SuperMenu.class.getName();

    @Resource
    private BaseMapper<SuperMenu> baseMapper;

    @Override
    public int insert(SuperMenu superMenu) {
        superMenu.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superMenu);
    }
    
    @Override
    public int delete(SuperMenu superMenu) {
        superMenu.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superMenu);
    }

    @Override
    public int update(SuperMenu superMenu) {
        superMenu.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superMenu);
    }

    @Override
    public List<SuperMenu> selectList(SuperMenu superMenu) {
        superMenu.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superMenu);
    }

    @Override
    public SuperMenu selectOne(SuperMenu superMenu) {
        superMenu.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superMenu);
    }
}
