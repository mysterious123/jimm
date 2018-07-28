package service.superrole;

import model.supermenu.SuperMenu;
import model.superpermission.SuperPermission;
import model.superrole.SuperRole;
import java.util.List;

public interface SuperRoleService {
    /**
     * 新增
     * @param superRole
     * @return
     */
    int insert(SuperRole superRole);

    /**
     * 删除
     * @param superRole
     * @return
     */
    int delete(SuperRole superRole);

    /**
     * 更新
     * @param superRole
     * @return
     */
    int update(SuperRole superRole);

    /**
     * 获取所有
     * @param superRole
     * @return
     */
    List<SuperRole> selectList(SuperRole superRole);

    /**
     * 获取单个
     * @param superRole
     * @return
     */
    SuperRole selectOne(SuperRole superRole);

    /**
     * 根据角色获取菜单
     * @param superRole
     * @return
     */
    List<SuperMenu>  selectMenuByRoleId(SuperRole superRole);


    /**
     * 根角色获取权限
     * @param superRole
     * @return
     */
    List<SuperPermission> selectPermissionByRoleId(SuperRole superRole);
}
