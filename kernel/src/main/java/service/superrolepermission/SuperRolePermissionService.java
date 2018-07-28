package service.superrolepermission;

import model.superrolepermission.SuperRolePermission;
import java.util.List;

public interface SuperRolePermissionService {
    /**
     * 新增
     * @param superRolePermission
     * @return
     */
    int insert(SuperRolePermission superRolePermission);

    /**
     * 删除
     * @param superRolePermission
     * @return
     */
    int delete(SuperRolePermission superRolePermission);

    /**
     * 更新
     * @param superRolePermission
     * @return
     */
    int update(SuperRolePermission superRolePermission);

    /**
     * 获取所有
     * @param superRolePermission
     * @return
     */
    List<SuperRolePermission> selectList(SuperRolePermission superRolePermission);

    /**
     * 获取单个
     * @param superRolePermission
     * @return
     */
    SuperRolePermission selectOne(SuperRolePermission superRolePermission);

    /**
     * 获取权限
     * @param superRolePermission
     * @param permission
     * @return
     */
    List<SuperRolePermission> getPermissions(SuperRolePermission superRolePermission, String permission);
}
