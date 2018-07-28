package service.superuser.impl;

import common.util.CryptoUtil;
import dao.BaseMapper;
import model.superrole.SuperRole;
import model.superroleuser.SuperRoleUser;
import model.superuser.SuperUser;
import model.superusergroup.SuperUserGroup;
import model.superusergroupLink.SuperUserGroupLink;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.superroleuser.SuperRoleUserService;
import service.superuser.SuperUserService;
import service.superusergroup.SuperUserGroupService;
import service.superusergroupLink.SuperUserGroupLinkService;

import javax.annotation.Resource;
import java.util.List;

@Service("SuperUserService")
public class SuperUserServiceImpl implements SuperUserService {
    private final Logger L = LoggerFactory.getLogger(SuperUserServiceImpl.class);
    private static final String NAMESPACE = SuperUser.class.getName();

    @Resource
    private BaseMapper<SuperUser> baseMapper;

    @Resource
    private SuperUserGroupService superUserGroupService;

    @Resource
    private SuperUserGroupLinkService superUserGroupLinkService;

    @Resource
    private SuperRoleUserService superRoleUserService;

    @Override
    public int insert(SuperUser superUser) {
        long datetime = DateTime.now().getMillis();
        superUser.setCreateTime(datetime);
        superUser.setStatmentId(NAMESPACE + ".insert");
        superUser.setPassword(CryptoUtil.encodePassphrase(superUser.getPassword(), superUser.getUserName()));
        baseMapper.insert(superUser);
        insertUserGroupLink(superUser);

        SuperRoleUser superRoleUser = new SuperRoleUser();
        superRoleUser.setUserId(superUser.getId());
        superRoleUserService.delete(superRoleUser);
        for(SuperRole superRole : superUser.getSuperRoles()){
            superRoleUser.setRoleId(superRole.getId());
            superRoleUserService.insert(superRoleUser);
        }
        return 0;
    }

    private void insertUserGroupLink(SuperUser superUser) {
        if (superUser.getGroupName() != null && !superUser.getGroupName().equals("")) {
            SuperUserGroup userGroup = new SuperUserGroup();
            userGroup.setGroupName(superUser.getGroupName());
            SuperUserGroup group = superUserGroupService.selectOne(userGroup);
            SuperUserGroupLink userGroupLink = new SuperUserGroupLink();
            userGroupLink.setGroupId(group.getId());
            userGroupLink.setUserId(superUser.getId());
            superUserGroupLinkService.insert(userGroupLink);
        }
    }

    @Override
    public int delete(SuperUser superUser) {
        SuperRoleUser superRoleUser = new SuperRoleUser();
        superRoleUser.setUserId(superUser.getId());
        superRoleUserService.delete(superRoleUser);

        superUser.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superUser);
    }

    @Override
    public int update(SuperUser superUser) {
        superUser.setStatmentId(NAMESPACE + ".update");
        SuperUserGroupLink userGroupLink = new SuperUserGroupLink();
        userGroupLink.setUserId(superUser.getId());
        superUserGroupLinkService.delete(userGroupLink);
        insertUserGroupLink(superUser);

        SuperRoleUser superRoleUser = new SuperRoleUser();
        superRoleUser.setUserId(superUser.getId());
        superRoleUserService.delete(superRoleUser);
        for(SuperRole superRole : superUser.getSuperRoles()){
            superRoleUser.setRoleId(superRole.getId());
            superRoleUserService.insert(superRoleUser);
        }
        return baseMapper.update(superUser);
    }

    @Override
    public List<SuperUser> selectList(SuperUser superUser) {
        superUser.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superUser);
    }

    @Override
    public SuperUser selectOne(SuperUser superUser) {
        superUser.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superUser);
    }

    @Override
    public Object queryUserInfo(SuperUser superUser) {
        superUser.setStatmentId(NAMESPACE + ".queryUserInfo");
        return baseMapper.selectList(superUser);
    }

    @Override
    public SuperUser selectOneInfo(SuperUser superUser) {
        superUser.setStatmentId(NAMESPACE + ".selectOneInfo");
        return baseMapper.selectOne(superUser);
    }
}
