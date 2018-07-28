package service.releasehistory.impl;

import dao.BaseMapper;
import model.releasehistory.ReleaseHistory;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.releasehistory.ReleaseHistoryService;

import javax.annotation.Resource;
import java.util.List;

@Service("ReleaseHistoryService")
public class ReleaseHistoryServiceImpl implements ReleaseHistoryService{
    private final Logger L = LoggerFactory.getLogger(ReleaseHistoryServiceImpl.class);
    private static final String NAMESPACE = ReleaseHistory.class.getName();

    @Resource
    private BaseMapper<ReleaseHistory> baseMapper;

    @Override
    public int insert(ReleaseHistory releaseHistory) {
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        long datetime = DateTime.now().getMillis();
        releaseHistory.setReleaseTime(datetime);
        releaseHistory.setReleasePerson(user.getUserName());
        releaseHistory.setReleaseResault("0");
        releaseHistory.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(releaseHistory);
    }
    
    @Override
    public int delete(ReleaseHistory releaseHistory) {
        releaseHistory.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(releaseHistory);
    }

    @Override
    public int update(ReleaseHistory releaseHistory) {
        releaseHistory.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(releaseHistory);
    }

    @Override
    public List<ReleaseHistory> selectList(ReleaseHistory releaseHistory) {
        releaseHistory.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(releaseHistory);
    }

    @Override
    public ReleaseHistory selectOne(ReleaseHistory releaseHistory) {
        releaseHistory.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(releaseHistory);
    }
}
