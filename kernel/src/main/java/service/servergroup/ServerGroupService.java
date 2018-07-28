package service.servergroup;

import model.servergroup.ServerGroup;
import java.util.List;

public interface ServerGroupService {
    /**
     * 新增
     * @param serverGroup
     * @return
     */
    int insert(ServerGroup serverGroup);

    /**
     * 删除
     * @param serverGroup
     * @return
     */
    int delete(ServerGroup serverGroup);

    /**
     * 更新
     * @param serverGroup
     * @return
     */
    int update(ServerGroup serverGroup);

    /**
     * 获取所有
     * @param serverGroup
     * @return
     */
    List<ServerGroup> selectList(ServerGroup serverGroup);

    /**
     * 获取单个
     * @param serverGroup
     * @return
     */
    ServerGroup selectOne(ServerGroup serverGroup);

    /**
     * 获取根和子节点
     * @param serverGroup
     * @return
     */
    List<ServerGroup>  selectLevel(ServerGroup serverGroup);
}
