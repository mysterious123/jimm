package service.superpermission;

import model.superpermission.SuperPermission;
import java.util.List;

public interface SuperPermissionService {
    /**
     * 新增
     * @param superPermission
     * @return
     */
    int insert(SuperPermission superPermission);

    /**
     * 删除
     * @param superPermission
     * @return
     */
    int delete(SuperPermission superPermission);

    /**
     * 更新
     * @param superPermission
     * @return
     */
    int update(SuperPermission superPermission);

    /**
     * 获取所有
     * @param superPermission
     * @return
     */
    List<SuperPermission> selectList(SuperPermission superPermission);

    /**
     * 获取单个
     * @param superPermission
     * @return
     */
    SuperPermission selectOne(SuperPermission superPermission);
}
