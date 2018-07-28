package service.superlogmanagement.impl;

import dao.BaseMapper;

import db.DataSource;
import db.DataSourceContextHolder;
import model.superlogmanagement.SuperLogManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superlogmanagement.SuperLogManagementService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperLogManagementService")
public class SuperLogManagementServiceImpl implements SuperLogManagementService{
    private final Logger L = LoggerFactory.getLogger(SuperLogManagementServiceImpl.class);
    private static final String NAMESPACE = SuperLogManagement.class.getName();

    @Resource
    private BaseMapper<SuperLogManagement> baseMapper;

    @Override
    @DataSource(DataSourceContextHolder.DATA_SOURCE_A)
    public int insert(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superLogManagement);
    }
    
    @Override
    public int delete(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superLogManagement);
    }

    @Override
    public int update(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superLogManagement);
    }

    @Override
    public List<SuperLogManagement> selectList(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superLogManagement);
    }

    @Override
    public SuperLogManagement selectOne(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superLogManagement);
    }

    @Override
    public List<SuperLogManagement> queryLogManagement(SuperLogManagement superLogManagement) {
        superLogManagement.setStatmentId(NAMESPACE + ".queryLogManagement");
        return baseMapper.selectList(superLogManagement);
    }
}
