package service.venderinfo.impl;

import dao.BaseMapper;
import model.venderdetailinfo.VenderDetailInfo;
import model.venderinfo.VenderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import service.venderdetailinfo.VenderDetailInfoService;
import service.venderinfo.VenderInfoService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;
import java.io.File;

@Service("VenderInfoService")
public class VenderInfoServiceImpl implements VenderInfoService,ServletContextAware {
    @Resource
    VenderDetailInfoService venderDetailInfoService;
    private final Logger L = LoggerFactory.getLogger(VenderInfoServiceImpl.class);
    private static final String NAMESPACE = VenderInfo.class.getName();
    private ServletContext context;

    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
    @Resource
    private BaseMapper<VenderInfo> baseMapper;

    @Override
    public int insert(VenderInfo venderInfo) {
        venderInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(venderInfo);
    }
    
    @Override
    public int delete(VenderInfo venderInfo) {
        venderInfo.setStatmentId(NAMESPACE + ".delete");
        VenderDetailInfo detailInfo = new VenderDetailInfo();
        detailInfo.setVenderId(venderInfo.getId());
        venderDetailInfoService.delete(detailInfo);
        return baseMapper.delete(venderInfo);
    }

    @Override
    public int update(VenderInfo venderInfo) {
        venderInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(venderInfo);
    }

    @Override
    public List<VenderInfo> selectList(VenderInfo venderInfo) {
        venderInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(venderInfo);
    }

    @Override
    public VenderInfo selectOne(VenderInfo venderInfo) {
        venderInfo.setStatmentId(NAMESPACE + ".selectOne");
       return baseMapper.selectOne(venderInfo);
    }

    @Override
    public void logoUpload(MultipartFile files){
        String fileName =  files.getOriginalFilename();
        String filePath = context.getRealPath("");
        File dest = new File(filePath +File.separator+ "resources"+File.separator+"images"+File.separator+"logo"+File.separator+fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try{
            files.transferTo(dest);
        }catch (IOException e){
            L.error("logo上传失败");
        }
    }
}
