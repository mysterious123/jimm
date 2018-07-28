package service.superlogo.impl;

import dao.BaseMapper;
import model.superlogo.SuperLogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import service.superlogo.SuperLogoService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("SuperLogoService")
public class SuperLogoServiceImpl implements SuperLogoService,ServletContextAware {
    private final Logger L = LoggerFactory.getLogger(SuperLogoServiceImpl.class);
    private static final String NAMESPACE = SuperLogo.class.getName();
    private ServletContext context;

    @Resource
    private BaseMapper<SuperLogo> baseMapper;

    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @Override
    public int insert(SuperLogo superLogo) {
        superLogo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(superLogo);
    }
    
    @Override
    public int delete(SuperLogo superLogo) {
        superLogo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(superLogo);
    }

    @Override
    public int update(SuperLogo superLogo) {
        superLogo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(superLogo);
    }

    @Override
    public List<SuperLogo> selectList(SuperLogo superLogo) {
        superLogo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(superLogo);
    }

    @Override
    public SuperLogo selectOne(SuperLogo superLogo) {
        superLogo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(superLogo);
    }

    @Override
    public void logoUpload(MultipartFile files) {
        String filePath = context.getRealPath("");
        File dest = new File(filePath +File.separator+ "resources"+File.separator+"images"+File.separator+"logo.png");
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
