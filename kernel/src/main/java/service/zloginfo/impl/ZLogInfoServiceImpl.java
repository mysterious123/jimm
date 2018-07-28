package service.zloginfo.impl;

import dao.BaseMapper;
import model.zloginfo.ZLogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.zloginfo.ZLogInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("ZLogInfoService")
public class ZLogInfoServiceImpl implements ZLogInfoService{
    private final Logger L = LoggerFactory.getLogger(ZLogInfoServiceImpl.class);
    private static final String NAMESPACE = ZLogInfo.class.getName();

    @Resource
    private BaseMapper<ZLogInfo> baseMapper;

    @Override
    public int insert(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(zLogInfo);
    }
    
    @Override
    public int delete(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(zLogInfo);
    }

    @Override
    public int update(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(zLogInfo);
    }

    @Override
    public List<ZLogInfo> selectList(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(zLogInfo);
    }

    @Override
    public ZLogInfo selectOne(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(zLogInfo);
    }

    @Override
    public int queryCount(ZLogInfo zLogInfo) {
        zLogInfo.setStatmentId(NAMESPACE + ".queryCount");
        return baseMapper.selectOne(zLogInfo,Integer.class);
    }
}
