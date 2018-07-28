package service.zmiboid.impl;

import dao.BaseMapper;
import model.zmiboid.ZMibOid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.zmiboid.ZMibOidService;

import javax.annotation.Resource;
import java.util.List;

@Service("ZMibOidService")
public class ZMibOidServiceImpl implements ZMibOidService{
    private final Logger L = LoggerFactory.getLogger(ZMibOidServiceImpl.class);
    private static final String NAMESPACE = ZMibOid.class.getName();

    @Resource
    private BaseMapper<ZMibOid> baseMapper;

    @Override
    public int insert(ZMibOid zMibOid) {
        zMibOid.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(zMibOid);
    }
    
    @Override
    public int delete(ZMibOid zMibOid) {
        zMibOid.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(zMibOid);
    }

    @Override
    public int update(ZMibOid zMibOid) {
        zMibOid.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(zMibOid);
    }

    @Override
    public List<ZMibOid> selectList(ZMibOid zMibOid) {
        zMibOid.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(zMibOid);
    }

    @Override
    public ZMibOid selectOne(ZMibOid zMibOid) {
        zMibOid.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(zMibOid);
    }
}
