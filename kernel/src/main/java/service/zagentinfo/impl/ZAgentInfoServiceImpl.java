package service.zagentinfo.impl;

import dao.BaseMapper;
import db.DataSource;
import db.DataSourceContextHolder;
import model.zagentcolumninfo.ZAgentColumnInfo;
import model.zagentinfo.ZAgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.zagentcolumninfo.ZAgentColumnInfoService;
import service.zagentinfo.ZAgentInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("ZAgentInfoService")
public class ZAgentInfoServiceImpl implements ZAgentInfoService{
    private final Logger L = LoggerFactory.getLogger(ZAgentInfoServiceImpl.class);
    private static final String NAMESPACE = ZAgentInfo.class.getName();

    @Resource
    private BaseMapper<ZAgentInfo> baseMapper;

    @Resource
    private ZAgentColumnInfoService zAgentColumnInfoService;



    @Override
    public int insert(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(zAgentInfo);
    }
    
    @Override
    public int delete(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".delete");
        ZAgentColumnInfo zAgentColumnInfo = new ZAgentColumnInfo();
        zAgentColumnInfo.setAgentId(zAgentInfo.getId());
        zAgentColumnInfoService.delete(zAgentColumnInfo);
        return baseMapper.delete(zAgentInfo);
    }

    @Override
    public int update(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(zAgentInfo);
    }


    @Override
    public List<ZAgentInfo> selectList(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(zAgentInfo);
    }

    @Override
    public ZAgentInfo selectOne(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(zAgentInfo);
    }

    @Override
    public int queryCount(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".queryCount");
        return baseMapper.selectOne(zAgentInfo,Integer.class);
    }

    @Override
    @DataSource(DataSourceContextHolder.DATA_SOURCE_B)
    public int createTable(ZAgentInfo zAgentInfo) {
        zAgentInfo.setStatmentId(NAMESPACE + ".createTable");
        return baseMapper.update(zAgentInfo);
    }


}
