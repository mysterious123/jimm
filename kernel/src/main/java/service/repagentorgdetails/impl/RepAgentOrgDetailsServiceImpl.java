package service.repagentorgdetails.impl;

import dao.BaseMapper;
import model.releaselink.ReleaseLink;
import model.repagentorgdetails.RepAgentOrgDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.releaselink.impl.ReleaseLinkServiceImpl;
import service.repagentorgdetails.RepAgentOrgDetailsService;

import javax.annotation.Resource;

/**
 * @Created with IDEA
 * @author:LiWangZhou
 * @Date:2018/7/27/027
 * @Time:13:26
 **/
@Service(" repAgentOrgDetailsService")
public class RepAgentOrgDetailsServiceImpl implements RepAgentOrgDetailsService {
    private final Logger L = LoggerFactory.getLogger(RepAgentOrgDetailsServiceImpl.class);
    private static final String NAMESPACE = RepAgentOrgDetails.class.getName();

    @Resource
    private BaseMapper<RepAgentOrgDetails> baseMapper;

    /**
     * 新增
     * @param repAgentOrgDetails
     * @return
     */
    @Override
    public int insert(RepAgentOrgDetails repAgentOrgDetails){
        repAgentOrgDetails.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert( repAgentOrgDetails);
    }

}
