package service.superroleuser.impl;

import dao.BaseMapper;
import model.superroleuser.SuperRoleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superroleuser.SuperRoleUserService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperRoleUserService")
public class SuperRoleUserServiceImpl implements SuperRoleUserService{
    private final Logger L = LoggerFactory.getLogger(SuperRoleUserServiceImpl.class);
    private static final String NAMESPACE = SuperRoleUser.class.getName();

    @Resource
    private BaseMapper<SuperRoleUser> baseMapper;

    @Override
    public int insert(SuperRoleUser superRoleUser) {
        superRoleUser.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superRoleUser);
    }
    
    @Override
    public int delete(SuperRoleUser superRoleUser) {
        superRoleUser.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superRoleUser);
    }

    @Override
    public int update(SuperRoleUser superRoleUser) {
        superRoleUser.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superRoleUser);
    }

    @Override
    public List<SuperRoleUser> selectList(SuperRoleUser superRoleUser) {
        superRoleUser.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superRoleUser);
    }

    @Override
    public SuperRoleUser selectOne(SuperRoleUser superRoleUser) {
        superRoleUser.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superRoleUser);
    }
}
