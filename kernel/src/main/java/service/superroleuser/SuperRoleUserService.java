package service.superroleuser;

import model.superroleuser.SuperRoleUser;
import java.util.List;

public interface SuperRoleUserService {
    /**
     * 新增
     * @param superRoleUser
     * @return
     */
    int insert(SuperRoleUser superRoleUser);

    /**
     * 删除
     * @param superRoleUser
     * @return
     */
    int delete(SuperRoleUser superRoleUser);

    /**
     * 更新
     * @param superRoleUser
     * @return
     */
    int update(SuperRoleUser superRoleUser);

    /**
     * 获取所有
     * @param superRoleUser
     * @return
     */
    List<SuperRoleUser> selectList(SuperRoleUser superRoleUser);

    /**
     * 获取单个
     * @param superRoleUser
     * @return
     */
    SuperRoleUser selectOne(SuperRoleUser superRoleUser);
}
