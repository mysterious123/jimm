package service.superlogmanagement;

import model.superlogmanagement.SuperLogManagement;
import java.util.List;

public interface SuperLogManagementService {
    /**
     * 新增
     * @param superLogManagement
     * @return
     */
    int insert(SuperLogManagement superLogManagement);

    /**
     * 删除
     * @param superLogManagement
     * @return
     */
    int delete(SuperLogManagement superLogManagement);

    /**
     * 更新
     * @param superLogManagement
     * @return
     */
    int update(SuperLogManagement superLogManagement);

    /**
     * 获取所有
     * @param superLogManagement
     * @return
     */
    List<SuperLogManagement> selectList(SuperLogManagement superLogManagement);

    /**
     * 获取单个
     * @param superLogManagement
     * @return
     */
    SuperLogManagement selectOne(SuperLogManagement superLogManagement);
    /**
     * 日志查询
     * @param superLogManagement
     * @return
     */
    List<SuperLogManagement>queryLogManagement(SuperLogManagement superLogManagement);
}
