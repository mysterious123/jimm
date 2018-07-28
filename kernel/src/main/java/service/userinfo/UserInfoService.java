package service.userinfo;

import model.userinfo.UserInfo;
import java.util.List;

public interface UserInfoService {
    /**
     * 新增
     * @param userInfo
     * @return
     */
    int insert(UserInfo userInfo);

    /**
     * 删除
     * @param userInfo
     * @return
     */
    int delete(UserInfo userInfo);

    /**
     * 更新
     * @param userInfo
     * @return
     */
    int update(UserInfo userInfo);

    /**
     * 获取所有
     * @param userInfo
     * @return
     */
    List<UserInfo> selectList(UserInfo userInfo);

    /**
     * 获取单个
     * @param userInfo
     * @return
     */
    UserInfo selectOne(UserInfo userInfo);


    List<UserInfo> queryGroupName(UserInfo userInfo);
}
