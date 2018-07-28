package service.managegroup;

import model.managegroup.ManageGroup;
import java.util.List;

public interface ManageGroupService {
    /**
     * 新增
     * @param manageGroup
     * @return
     */
    int insert(ManageGroup manageGroup);

    /**
     * 删除
     * @param manageGroup
     * @return
     */
    int delete(ManageGroup manageGroup);

    /**
     * 更新
     * @param manageGroup
     * @return
     */
    int update(ManageGroup manageGroup);

    /**
     * 获取所有
     * @param manageGroup
     * @return
     */
    List<ManageGroup> selectList(ManageGroup manageGroup);

    /**
     * 获取单个
     * @param manageGroup
     * @return
     */
    ManageGroup selectOne(ManageGroup manageGroup);
}
