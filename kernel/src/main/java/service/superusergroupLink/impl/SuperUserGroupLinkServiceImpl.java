package service.superusergroupLink.impl;

import dao.BaseMapper;
import model.superusergroupLink.SuperUserGroupLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superusergroupLink.SuperUserGroupLinkService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperUserGroupLinkService")
public class SuperUserGroupLinkServiceImpl implements SuperUserGroupLinkService{
    private final Logger L = LoggerFactory.getLogger(SuperUserGroupLinkServiceImpl.class);
    private static final String NAMESPACE = SuperUserGroupLink.class.getName();

    @Resource
    private BaseMapper<SuperUserGroupLink> baseMapper;

    @Override
    public int insert(SuperUserGroupLink superUserGroupLink) {
        superUserGroupLink.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superUserGroupLink);

    }
    
    @Override
    public int delete(SuperUserGroupLink superUserGroupLink) {
        superUserGroupLink.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superUserGroupLink);
    }

    @Override
    public int update(SuperUserGroupLink superUserGroupLink) {
        superUserGroupLink.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superUserGroupLink);
    }

    @Override
    public List<SuperUserGroupLink> selectList(SuperUserGroupLink superUserGroupLink) {
        superUserGroupLink.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superUserGroupLink);
    }

    @Override
    public SuperUserGroupLink selectOne(SuperUserGroupLink superUserGroupLink) {
        superUserGroupLink.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superUserGroupLink);
    }
}
