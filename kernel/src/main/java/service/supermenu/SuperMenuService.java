package service.supermenu;

import model.supermenu.SuperMenu;
import java.util.List;

public interface SuperMenuService {
    /**
     * 新增
     * @param superMenu
     * @return
     */
    int insert(SuperMenu superMenu);

    /**
     * 删除
     * @param superMenu
     * @return
     */
    int delete(SuperMenu superMenu);

    /**
     * 更新
     * @param superMenu
     * @return
     */
    int update(SuperMenu superMenu);

    /**
     * 获取所有
     * @param superMenu
     * @return
     */
    List<SuperMenu> selectList(SuperMenu superMenu);

    /**
     * 获取单个
     * @param superMenu
     * @return
     */
    SuperMenu selectOne(SuperMenu superMenu);
}
