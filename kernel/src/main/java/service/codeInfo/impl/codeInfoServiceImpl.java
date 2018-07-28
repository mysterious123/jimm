package service.codeInfo.impl;

import dao.BaseMapper;
import model.codeInfo.codeInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.codeInfo.codeInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("codeInfoService")
public class codeInfoServiceImpl implements codeInfoService{
    private final Logger L = LoggerFactory.getLogger(codeInfoServiceImpl.class);
    private static final String NAMESPACE = codeInfo.class.getName();

    @Resource
    private BaseMapper<codeInfo> baseMapper;

    @Override
    public int insert(codeInfo codeInfo) {
        long datetime = DateTime.now().getMillis();
        codeInfo.setCreateDate(datetime);
        codeInfo.setUpdateDate(datetime);
        codeInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(codeInfo);
    }
    
    @Override
    public int delete(codeInfo codeInfo) {
        codeInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(codeInfo);
    }
    @Override
    public int update(codeInfo codeInfo) {
        long datetime = DateTime.now().getMillis();
        codeInfo.setUpdateDate(datetime);
        codeInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(codeInfo);
    }

    @Override
    public List<codeInfo> selectList(codeInfo codeInfo) {
        codeInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(codeInfo);
    }

    @Override
    public codeInfo selectOne(codeInfo codeInfo) {
        codeInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(codeInfo);
    }
}
