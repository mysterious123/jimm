package service.superusergroup;

import model.superuser.SuperUser;
import model.superusergroup.SuperUserGroup;
import java.util.List;

public interface SuperUserGroupService {
    /**
     * 新增
     * @param superUserGroup
     * @return
     */
    int insert(SuperUserGroup superUserGroup);

    /**
     * 删除
     * @param superUserGroup
     * @return
     */
    int delete(SuperUserGroup superUserGroup);

    /**
     * 更新
     * @param superUserGroup
     * @return
     */
    int update(SuperUserGroup superUserGroup);

    /**
     * 获取所有
     * @param superUserGroup
     * @return
     */
    List<SuperUserGroup> selectList(SuperUserGroup superUserGroup);

    /**
     * 获取单个
     * @param superUserGroup
     * @return
     */
    SuperUserGroup selectOne(SuperUserGroup superUserGroup);

    /**
     * 查询管理组已选择的用户
     * @param superUserGroup
     * @return
     */
    List<SuperUser> querySelectUser(SuperUserGroup superUserGroup);

    /**
     * 查询管理组未选择的用户
     * @param superUserGroup
     * @return
     */
    List<SuperUser> queryNoSelectUser(SuperUserGroup superUserGroup);
}
