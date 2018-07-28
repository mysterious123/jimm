package service.userinfo.impl;

import dao.BaseMapper;
import model.managegroupinfo.ManageGroupInfo;
import model.mcrelation.McRelation;
import model.userinfo.UserInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.mcrelation.McRelationService;
import service.userinfo.UserInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService{
    private final Logger L = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    private static final String NAMESPACE = UserInfo.class.getName();

    @Resource
    private BaseMapper<UserInfo> baseMapper;
    @Resource
    McRelationService mcRelationService;

    @Override
    public int insert(UserInfo userInfo) {
        long datetime = DateTime.now().getMillis();
        userInfo.setCreateDate(datetime);
        userInfo.setStatmentId(NAMESPACE + ".insert");
        baseMapper.insert(userInfo);
        McRelation mcRelation = new McRelation();
        if(userInfo.getManageGroups() != null){
            for(ManageGroupInfo manageGroupInfo : userInfo.getManageGroups()){
                mcRelation.setContactsId(userInfo.getId());
                mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
                mcRelationService.insert(mcRelation);

            }
        }
        return 0;
    }
    
    @Override
    public int delete(UserInfo userInfo) {
        userInfo.setStatmentId(NAMESPACE + ".delete");
        McRelation mcRelation = new McRelation();
        mcRelation.setContactsId(userInfo.getId());
        mcRelationService.delete(mcRelation);
        return baseMapper.delete(userInfo);
    }

    @Override
    public int update(UserInfo userInfo) {
        McRelation mcRelation = new McRelation();
        mcRelation.setContactsId(userInfo.getId());
        mcRelationService.delete(mcRelation);
        if(userInfo.getManageGroups() != null){
            for(ManageGroupInfo manageGroupInfo : userInfo.getManageGroups()){
                mcRelation.setContactsId(userInfo.getId());
                mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
                mcRelationService.insert(mcRelation);
            }
        }
        userInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(userInfo);
    }

    @Override
    public List<UserInfo> selectList(UserInfo userInfo) {
        userInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(userInfo);
    }

    @Override
    public UserInfo selectOne(UserInfo userInfo) {
        userInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(userInfo);
    }

    @Override
    public List<UserInfo> queryGroupName(UserInfo userInfo) {
        userInfo.setStatmentId(NAMESPACE + ".queryGroupName");
        return baseMapper.selectList(userInfo);
    }
}
