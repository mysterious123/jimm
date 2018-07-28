package service.managegroupinfo.impl;

import dao.BaseMapper;
import model.managegroupinfo.ManageGroupInfo;
import model.mcrelation.McRelation;
import model.userinfo.UserInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.managegroupinfo.ManageGroupInfoService;
import service.mcrelation.McRelationService;
import service.userinfo.UserInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("ManageGroupInfoService")
public class ManageGroupInfoServiceImpl implements ManageGroupInfoService {
    private final Logger L = LoggerFactory.getLogger(ManageGroupInfoServiceImpl.class);
    private static final String NAMESPACE = ManageGroupInfo.class.getName();

    @Resource
    private BaseMapper<ManageGroupInfo> baseMapper;

    @Resource
    private McRelationService mcRelationService;

    @Override
    public int insert(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".insert");
        manageGroupInfo.setCreateTime(DateTime.now().getMillis());
        //TODO 暂时没有用户所以创建人填0
        manageGroupInfo.setUserId("0");
        baseMapper.insert(manageGroupInfo);
        for(McRelation mcRelation : manageGroupInfo.getMcRelations()){
            mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
            mcRelationService.insert(mcRelation);
        }
        return 1;
    }

    @Override
    public int delete(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".delete");
        McRelation mcRelation = new McRelation();
        mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
        mcRelationService.delete(mcRelation);
        return baseMapper.delete(manageGroupInfo);
    }

    @Override
    public int update(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".update");
        //先删除关联用户再插入
        McRelation mcRelation = new McRelation();
        mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
        mcRelationService.delete(mcRelation);
        for(McRelation mr : manageGroupInfo.getMcRelations()){
            mcRelation.setManageGroupInfoId(manageGroupInfo.getId());
            mcRelationService.insert(mr);
        }
        return baseMapper.update(manageGroupInfo);
    }

    @Override
    public List<ManageGroupInfo> selectList(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(manageGroupInfo);
    }

    @Override
    public ManageGroupInfo selectOne(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(manageGroupInfo);
    }

    @Override
    public List<UserInfo> querySelectUser(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".querySelectUser");
        return baseMapper.selectList(manageGroupInfo,UserInfo.class);
    }

    @Override
    public List<UserInfo> queryNoSelectUser(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setStatmentId(NAMESPACE + ".queryNoSelectUser");
        return baseMapper.selectList(manageGroupInfo,UserInfo.class);
    }
}
