package service.serverinfo;

import model.serverinfo.ServerInfo;
import java.util.List;

public interface ServerInfoService {
    /**
     * 新增
     * @param serverInfo
     * @return
     */
    int insert(ServerInfo serverInfo);

    /**
     * 删除
     * @param serverInfo
     * @return
     */
    int delete(ServerInfo serverInfo);

    /**
     * 更新
     * @param serverInfo
     * @return
     */
    int update(ServerInfo serverInfo);

    /**
     * 获取所有
     * @param serverInfo
     * @return
     */
    List<ServerInfo> selectList(ServerInfo serverInfo);

    /**
     * 获取单个
     * @param serverInfo
     * @return
     */
    ServerInfo selectOne(ServerInfo serverInfo);

    int checkIpExist(ServerInfo serverInfo);

    List<ServerInfo> getServerByGroupId(ServerInfo serverInfo);

    /**
     * 查询服务器策略
     * @param serverInfo
     * @return
     */
    List<ServerInfo> getPolicyInfo (ServerInfo serverInfo);
}
