package service.zmibinfo.impl;

import dao.BaseMapper;
import model.zmibinfo.ZMibInfo;
import model.zmiboid.ZMibOid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.zmibinfo.ZMibInfoService;
import service.zmiboid.ZMibOidService;

import javax.annotation.Resource;
import java.util.List;

@Service("ZMibInfoService")
public class ZMibInfoServiceImpl implements ZMibInfoService{
    private final Logger L = LoggerFactory.getLogger(ZMibInfoServiceImpl.class);
    private static final String NAMESPACE = ZMibInfo.class.getName();

    @Resource
    private BaseMapper<ZMibInfo> baseMapper;

    @Resource
    private ZMibOidService zMibOidService;

    @Override
    public int insert(ZMibInfo zMibInfo) {
        zMibInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(zMibInfo);
    }
    
    @Override
    public int delete(ZMibInfo zMibInfo) {
        ZMibOid zMibOid = new ZMibOid();
        zMibOid.setMibId(zMibInfo.getId());
        zMibOidService.delete(zMibOid);
        zMibInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(zMibInfo);
    }

    @Override
    public int update(ZMibInfo zMibInfo) {
        zMibInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(zMibInfo);
    }

    @Override
    public List<ZMibInfo> selectList(ZMibInfo zMibInfo) {
        zMibInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(zMibInfo);
    }

    @Override
    public ZMibInfo selectOne(ZMibInfo zMibInfo) {
        zMibInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(zMibInfo);
    }

    @Override
    public int queryCount(ZMibInfo zMibInfo) {
        zMibInfo.setStatmentId(NAMESPACE + ".queryCount");
        return baseMapper.selectOne(zMibInfo,Integer.class);
    }

}
