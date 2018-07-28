package service.zagentinfo;

import model.zagentinfo.ZAgentInfo;
import java.util.List;

public interface ZAgentInfoService {
    /**
     * 新增
     * @param zAgentInfo
     * @return
     */
    int insert(ZAgentInfo zAgentInfo);

    /**
     * 删除
     * @param zAgentInfo
     * @return
     */
    int delete(ZAgentInfo zAgentInfo);

    /**
     * 更新
     * @param zAgentInfo
     * @return
     */
    int update(ZAgentInfo zAgentInfo);

    /**
     * 获取所有
     * @param zAgentInfo
     * @return
     */
    List<ZAgentInfo> selectList(ZAgentInfo zAgentInfo);

    /**
     * 获取单个
     * @param zAgentInfo
     * @return
     */
    ZAgentInfo selectOne(ZAgentInfo zAgentInfo);

    /**
     * 获取数量
     * @param agentInfo
     * @return
     */
    int queryCount(ZAgentInfo zAgentInfo);

    //创建表
    int createTable(ZAgentInfo zAgentInfo);
}
