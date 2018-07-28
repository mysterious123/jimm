package service.venderdetailinfo.impl;

import dao.BaseMapper;
import model.venderdetailinfo.VenderDetailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.venderdetailinfo.VenderDetailInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("VenderDetailInfoService")
public class VenderDetailInfoServiceImpl implements VenderDetailInfoService{
    private final Logger L = LoggerFactory.getLogger(VenderDetailInfoServiceImpl.class);
    private static final String NAMESPACE = VenderDetailInfo.class.getName();

    @Resource
    private BaseMapper<VenderDetailInfo> baseMapper;

    @Override
    public int insert(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(venderDetailInfo);
    }
    
    @Override
    public int delete(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(venderDetailInfo);
    }

    @Override
    public int update(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(venderDetailInfo);
    }

    @Override
    public List<VenderDetailInfo> selectList(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(venderDetailInfo);
    }

    @Override
    public VenderDetailInfo selectOne(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(venderDetailInfo);
    }

    @Override
    public int checkObjectIdExist(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setStatmentId(NAMESPACE + ".selectOne");
        VenderDetailInfo detailInfo = baseMapper.selectOne(venderDetailInfo);
        if(detailInfo == null){
            return 0;
        }else{
            return 1;
        }
    }
}
