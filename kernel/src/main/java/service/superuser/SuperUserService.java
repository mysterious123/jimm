package service.superuser;

import model.superuser.SuperUser;
import java.util.List;

public interface SuperUserService {
    /**
     * 新增
     * @param superUser
     * @return
     */
    int insert(SuperUser superUser);

    /**
     * 删除
     * @param superUser
     * @return
     */
    int delete(SuperUser superUser);

    /**
     * 更新
     * @param superUser
     * @return
     */
    int update(SuperUser superUser);

    /**
     * 获取所有
     * @param superUser
     * @return
     */
    List<SuperUser> selectList(SuperUser superUser);

    /**
     * 获取单个
     * @param superUser
     * @return
     */
    SuperUser selectOne(SuperUser superUser);
    Object queryUserInfo(SuperUser superUser);

    /**
     * 查询包含角色权限的用户信息
     * @param superUser
     * @return
     */
    SuperUser selectOneInfo(SuperUser superUser);
}
