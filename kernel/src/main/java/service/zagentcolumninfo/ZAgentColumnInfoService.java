package service.zagentcolumninfo;

import model.zagentcolumninfo.ZAgentColumnInfo;
import java.util.List;

public interface ZAgentColumnInfoService {
    /**
     * 新增
     * @param zAgentColumnInfo
     * @return
     */
    int insert(ZAgentColumnInfo zAgentColumnInfo);

    /**
     * 删除
     * @param zAgentColumnInfo
     * @return
     */
    int delete(ZAgentColumnInfo zAgentColumnInfo);

    /**
     * 更新
     * @param zAgentColumnInfo
     * @return
     */
    int update(ZAgentColumnInfo zAgentColumnInfo);

    /**
     * 获取所有
     * @param zAgentColumnInfo
     * @return
     */
    List<ZAgentColumnInfo> selectList(ZAgentColumnInfo zAgentColumnInfo);

    /**
     * 获取单个
     * @param zAgentColumnInfo
     * @return
     */
    ZAgentColumnInfo selectOne(ZAgentColumnInfo zAgentColumnInfo);
}
