package service.superusergroupLink;

import model.superusergroupLink.SuperUserGroupLink;
import java.util.List;

public interface SuperUserGroupLinkService {

    /**
     * 新增
     * @param superUserGroupLink
     * @return
     */
    int insert(SuperUserGroupLink superUserGroupLink);

    /**
     * 删除
     * @param superUserGroupLink
     * @return
     */
    int delete(SuperUserGroupLink superUserGroupLink);

    /**
     * 更新
     * @param superUserGroupLink
     * @return
     */
    int update(SuperUserGroupLink superUserGroupLink);

    /**
     * 获取所有
     * @param superUserGroupLink
     * @return
     */
    List<SuperUserGroupLink> selectList(SuperUserGroupLink superUserGroupLink);

    /**
     * 获取单个
     * @param superUserGroupLink
     * @return
     */
    SuperUserGroupLink selectOne(SuperUserGroupLink superUserGroupLink);
}
