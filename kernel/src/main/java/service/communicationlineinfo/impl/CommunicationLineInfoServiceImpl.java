package service.communicationlineinfo.impl;

import dao.BaseMapper;
import model.communicationlineinfo.CommunicationLineInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.communicationlineinfo.CommunicationLineInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("communicationLineInfoService")
public class CommunicationLineInfoServiceImpl implements CommunicationLineInfoService {
    private final Logger L = LoggerFactory.getLogger(CommunicationLineInfoServiceImpl.class);
    private static final String NAMESPACE = CommunicationLineInfo.class.getName();

    @Resource
    private BaseMapper<CommunicationLineInfo> baseMapper;

    @Override
    public int insert(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(communicationLineInfo);
    }
    
    @Override
    public int delete(CommunicationLineInfo CommunicationLineInfo) {
        CommunicationLineInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(CommunicationLineInfo);
    }

    @Override
    public int update(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(communicationLineInfo);
    }

    @Override
    public List<CommunicationLineInfo> selectList(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(communicationLineInfo);
    }

    @Override
    public CommunicationLineInfo selectOne(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(communicationLineInfo);
    }

    @Override
    public int checkLineNameExist(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setStatmentId(NAMESPACE + ".selectOne");
        CommunicationLineInfo lineInfo = baseMapper.selectOne(communicationLineInfo);
        if(lineInfo == null){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public List<CommunicationLineInfo> getLineByGroupId(CommunicationLineInfo communicationLineInfo) {
        CommunicationLineInfo lineInfo = new CommunicationLineInfo();
        if(communicationLineInfo.getGroupId() != null && communicationLineInfo.getGroupId().contains(",")){
            lineInfo.setPaging(false);
            lineInfo.setStatmentId(NAMESPACE + ".getLineByGroupId");
            String groupIds[] = communicationLineInfo.getGroupId().substring(0,communicationLineInfo.getGroupId().length()-1).split(",");
            List groupIdList = new ArrayList();
            for(String groupId : groupIds){
                groupIdList.add(groupId);
            }
            lineInfo.setGroupIds(groupIdList);
            return baseMapper.selectList(lineInfo);
        }else{
            communicationLineInfo.setStatmentId(NAMESPACE + ".selectList");
            return baseMapper.selectList(communicationLineInfo);
        }
    }
}
