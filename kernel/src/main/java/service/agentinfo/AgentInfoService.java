package service.agentinfo;

import model.agentinfo.AgentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AgentInfoService {
    /**
     * 新增
     * @param agentInfo
     * @return
     */
    int insert(AgentInfo agentInfo);

    /**
     * 删除
     * @param agentInfo
     * @return
     */
    int delete(AgentInfo agentInfo);

    /**
     * 更新
     * @param agentInfo
     * @return
     */
    int update(AgentInfo agentInfo);

    /**
     * 获取所有
     * @param agentInfo
     * @return
     */
    List<AgentInfo> selectList(AgentInfo agentInfo);

    /**
     * 获取单个
     * @param agentInfo
     * @return
     */
    AgentInfo selectOne(AgentInfo agentInfo);

    /**
     * 获取
     * @param agentInfo
     * @return
     */
    List<AgentInfo> queryTypeCounts(AgentInfo agentInfo);

    void uploadAgentFile(MultipartFile files);
}
