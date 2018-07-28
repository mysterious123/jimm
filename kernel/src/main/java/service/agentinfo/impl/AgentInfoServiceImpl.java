package service.agentinfo.impl;

import dao.BaseMapper;
import model.agentinfo.AgentInfo;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import service.agentinfo.AgentInfoService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("AgentInfoService")
public class AgentInfoServiceImpl implements AgentInfoService,ServletContextAware {
    private final Logger L = LoggerFactory.getLogger(AgentInfoServiceImpl.class);
    private static final String NAMESPACE = AgentInfo.class.getName();
    private ServletContext context;
    @Resource
    private BaseMapper<AgentInfo> baseMapper;
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
    @Override
    public int insert(AgentInfo agentInfo) {
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        long datetime = DateTime.now().getMillis();
        agentInfo.setCreateTime(datetime);
        agentInfo.setFounder(user.getUserName());
        agentInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(agentInfo);
    }
    
    @Override
    public int delete(AgentInfo agentInfo) {
        agentInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(agentInfo);
    }

    @Override
    public int update(AgentInfo agentInfo) {
        agentInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(agentInfo);
    }

    @Override
    public List<AgentInfo> selectList(AgentInfo agentInfo) {
        agentInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(agentInfo);
    }

    @Override
    public AgentInfo selectOne(AgentInfo agentInfo) {
        agentInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(agentInfo);
    }

    @Override
    public List<AgentInfo> queryTypeCounts(AgentInfo agentInfo) {
        agentInfo.setStatmentId(NAMESPACE + ".queryTypeCounts");
        return baseMapper.selectList(agentInfo);
    }

    @Override
    public void uploadAgentFile(MultipartFile files) {
        String fileName =  files.getOriginalFilename();
        String filePath = context.getRealPath("");
        File dest = new File(filePath +File.separator+ "resources"+File.separator+"files"+File.separator+fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try{
            files.transferTo(dest);
        }catch (IOException e){
            L.error("代理上传失败");
        }
    }
}
