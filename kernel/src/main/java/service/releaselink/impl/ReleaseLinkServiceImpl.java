package service.releaselink.impl;

import dao.BaseMapper;
import model.releaselink.ReleaseLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.releaselink.ReleaseLinkService;

import javax.annotation.Resource;
import java.util.List;

@Service("releaseLinkService")
public class ReleaseLinkServiceImpl implements ReleaseLinkService {
    private final Logger L = LoggerFactory.getLogger(ReleaseLinkServiceImpl.class);
    private static final String NAMESPACE = ReleaseLink.class.getName();

    @Resource
    private BaseMapper<ReleaseLink> baseMapper;

    @Override
    public int insert(ReleaseLink releaseLink) {
        releaseLink.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(releaseLink);
    }
    
    @Override
    public int delete(ReleaseLink releaseLink) {
        releaseLink.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(releaseLink);
    }

    @Override
    public int update(ReleaseLink releaseLink) {
        releaseLink.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(releaseLink);
    }

    @Override
    public List<ReleaseLink> selectList(ReleaseLink releaseLink) {
        releaseLink.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(releaseLink);
    }

    @Override
    public ReleaseLink selectOne(ReleaseLink releaseLink) {
        releaseLink.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(releaseLink);
    }
}
