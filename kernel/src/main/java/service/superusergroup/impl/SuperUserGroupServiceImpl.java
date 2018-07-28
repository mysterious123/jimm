package service.superusergroup.impl;

import dao.BaseMapper;
import model.superuser.SuperUser;
import model.superusergroup.SuperUserGroup;
import model.superusergroupLink.SuperUserGroupLink;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superuser.SuperUserService;
import service.superusergroup.SuperUserGroupService;
import service.superusergroupLink.SuperUserGroupLinkService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperUserGroupService")
public class SuperUserGroupServiceImpl implements SuperUserGroupService{
    private final Logger L = LoggerFactory.getLogger(SuperUserGroupServiceImpl.class);
    private static final String NAMESPACE = SuperUserGroup.class.getName();

    @Resource
    private BaseMapper<SuperUserGroup> baseMapper;
    @Resource
    SuperUserGroupLinkService superUserGroupLinkSerbvice;

    @Override
    public int insert(SuperUserGroup superUserGroup) {
        long datetime = DateTime.now().getMillis();
        superUserGroup.setCreateTime(datetime);
        superUserGroup.setStatmentId(NAMESPACE + ".insert");
        baseMapper.insert(superUserGroup);

        if(superUserGroup.getSuperUserIds()!= null && superUserGroup.getSuperUserIds().size()>0){
            for(SuperUserGroupLink userGroupLink: superUserGroup.getSuperUserIds()){
                //一个用户只能属于一个组
                superUserGroupLinkSerbvice.delete(userGroupLink);
                userGroupLink.setGroupId(superUserGroup.getId());
                superUserGroupLinkSerbvice.insert(userGroupLink);
            }
        }
        return 0;
    }


    @Override
    public int delete(SuperUserGroup superUserGroup) {
        superUserGroup.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superUserGroup);
    }

    @Override
    public int update(SuperUserGroup superUserGroup) {
        SuperUserGroupLink groupLink = new SuperUserGroupLink();
        groupLink.setGroupId(superUserGroup.getId());
        superUserGroupLinkSerbvice.delete(groupLink);
        if(superUserGroup.getSuperUserIds()!= null && superUserGroup.getSuperUserIds().size()>0){
            for(SuperUserGroupLink userGroupLink: superUserGroup.getSuperUserIds()){
                //一个用户只能属于一个组
                superUserGroupLinkSerbvice.delete(userGroupLink);
                userGroupLink.setGroupId(superUserGroup.getId());
                superUserGroupLinkSerbvice.insert(userGroupLink);
            }
        }
        superUserGroup.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superUserGroup);
    }

    @Override
    public List<SuperUserGroup> selectList(SuperUserGroup superUserGroup) {
        superUserGroup.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superUserGroup);
    }

    @Override
    public SuperUserGroup selectOne(SuperUserGroup superUserGroup) {
        superUserGroup.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superUserGroup);
    }

    @Override
    public List<SuperUser> querySelectUser(SuperUserGroup superUserGroup) {
        superUserGroup.setStatmentId(NAMESPACE + ".querySelectUser");
        return baseMapper.selectList(superUserGroup, SuperUser.class);
    }

    @Override
    public List<SuperUser> queryNoSelectUser(SuperUserGroup superUserGroup) {
        superUserGroup.setStatmentId(NAMESPACE + ".queryNoSelectUser");
        return baseMapper.selectList(superUserGroup, SuperUser.class);
    }

}
