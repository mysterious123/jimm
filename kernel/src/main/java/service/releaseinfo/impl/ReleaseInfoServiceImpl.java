package service.releaseinfo.impl;

import dao.BaseMapper;
import model.releaseinfo.ReleaseInfo;
import model.releaselink.ReleaseLink;
import model.servergroup.ServerGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.releaseinfo.ReleaseInfoService;
import service.releaselink.ReleaseLinkService;

import javax.annotation.Resource;
import java.util.List;

@Service("releaseInfoService")
public class ReleaseInfoServiceImpl implements ReleaseInfoService {
    private final Logger L = LoggerFactory.getLogger(ReleaseInfoServiceImpl.class);
    private static final String NAMESPACE = ReleaseInfo.class.getName();

    @Resource
    private BaseMapper<ReleaseInfo> baseMapper;

    @Resource
    ReleaseLinkService releaseLinkService;

    @Override
    public int insert(ReleaseInfo releaseInfo) {
        releaseInfo.setStatmentId(NAMESPACE + ".insert");
        baseMapper.insert(releaseInfo);
        for (ReleaseLink link : releaseInfo.getReleaseLink()) {
            link.setReleaseId(releaseInfo.getId());
            releaseLinkService.insert(link);
        }
        return 1;
    }

    @Override
    public int delete(ReleaseInfo releaseInfo) {
        releaseInfo.setStatmentId(NAMESPACE + ".delete");
        ReleaseLink releaseLink = new ReleaseLink();
        releaseLink.setReleaseId(releaseInfo.getId());
        releaseLinkService.delete(releaseLink);
        return baseMapper.delete(releaseInfo);
    }

    @Override
    public int update(ReleaseInfo releaseInfo) {
        releaseInfo.setStatmentId(NAMESPACE + ".update");
        ReleaseLink releaseLink = new ReleaseLink();
        releaseLink.setReleaseId(releaseInfo.getId());
        releaseLinkService.delete(releaseLink);
        for(ReleaseLink link : releaseInfo.getReleaseLink()){
            link.setReleaseId(releaseInfo.getId());
            releaseLinkService.insert(link);
        }
        return baseMapper.update(releaseInfo);
    }

    @Override
    public List<ReleaseInfo> selectList(ReleaseInfo releaseInfo) {
        releaseInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(releaseInfo);
    }

    @Override
    public ReleaseInfo selectOne(ReleaseInfo releaseInfo) {
        releaseInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(releaseInfo);
    }

    @Override
    public List<ServerGroup> querySelectGroup (ReleaseInfo releaseInfo){
        releaseInfo.setStatmentId(NAMESPACE + ".querySelectGroup");
        return baseMapper.selectList(releaseInfo, ServerGroup.class);
    }
}
