package service.superrolemenu;

import model.superrolemenu.SuperRoleMenu;
import java.util.List;

public interface SuperRoleMenuService {
    /**
     * 新增
     * @param superRoleMenu
     * @return
     */
    int insert(SuperRoleMenu superRoleMenu);

    /**
     * 删除
     * @param superRoleMenu
     * @return
     */
    int delete(SuperRoleMenu superRoleMenu);

    /**
     * 更新
     * @param superRoleMenu
     * @return
     */
    int update(SuperRoleMenu superRoleMenu);

    /**
     * 获取所有
     * @param superRoleMenu
     * @return
     */
    List<SuperRoleMenu> selectList(SuperRoleMenu superRoleMenu);

    /**
     * 获取单个
     * @param superRoleMenu
     * @return
     */
    SuperRoleMenu selectOne(SuperRoleMenu superRoleMenu);
}
