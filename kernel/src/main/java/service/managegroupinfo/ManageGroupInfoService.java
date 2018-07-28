package service.managegroupinfo;

import model.managegroupinfo.ManageGroupInfo;
import model.userinfo.UserInfo;

import java.util.List;

public interface ManageGroupInfoService {
    /**
     * 新增
     * @param manageGroupInfo
     * @return
     */
    int insert(ManageGroupInfo manageGroupInfo);

    /**
     * 删除
     * @param manageGroupInfo
     * @return
     */
    int delete(ManageGroupInfo manageGroupInfo);

    /**
     * 更新
     * @param manageGroupInfo
     * @return
     */
    int update(ManageGroupInfo manageGroupInfo);

    /**
     * 获取所有
     * @param manageGroupInfo
     * @return
     */
    List<ManageGroupInfo> selectList(ManageGroupInfo manageGroupInfo);

    /**
     * 获取单个
     * @param manageGroupInfo
     * @return
     */
    ManageGroupInfo selectOne(ManageGroupInfo manageGroupInfo);

    /**
     * 查询管理组已选择的用户
     * @param manageGroupInfo
     * @return
     */
    List<UserInfo> querySelectUser(ManageGroupInfo manageGroupInfo);

    /**
     * 查询管理组未选择的用户
     * @param manageGroupInfo
     * @return
     */
    List<UserInfo> queryNoSelectUser(ManageGroupInfo manageGroupInfo);

}
