package service.zagentcolumninfo.impl;

import dao.BaseMapper;
import model.zagentcolumninfo.ZAgentColumnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.zagentcolumninfo.ZAgentColumnInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("ZAgentColumnInfoService")
public class ZAgentColumnInfoServiceImpl implements ZAgentColumnInfoService{
    private final Logger L = LoggerFactory.getLogger(ZAgentColumnInfoServiceImpl.class);
    private static final String NAMESPACE = ZAgentColumnInfo.class.getName();

    @Resource
    private BaseMapper<ZAgentColumnInfo> baseMapper;

    @Override
    public int insert(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(zAgentColumnInfo);
    }
    
    @Override
    public int delete(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(zAgentColumnInfo);
    }

    @Override
    public int update(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(zAgentColumnInfo);
    }

    @Override
    public List<ZAgentColumnInfo> selectList(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(zAgentColumnInfo);
    }

    @Override
    public ZAgentColumnInfo selectOne(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(zAgentColumnInfo);
    }
}
