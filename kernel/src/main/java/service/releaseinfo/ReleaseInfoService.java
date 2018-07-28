package service.releaseinfo;

import model.releaseinfo.ReleaseInfo;
import model.servergroup.ServerGroup;

import java.util.List;

public interface ReleaseInfoService {
    /**
     * 新增
     * @param releaseInfo
     * @return
     */
    int insert(ReleaseInfo releaseInfo);

    /**
     * 删除
     * @param releaseInfo
     * @return
     */
    int delete(ReleaseInfo releaseInfo);

    /**
     * 更新
     * @param releaseInfo
     * @return
     */
    int update(ReleaseInfo releaseInfo);

    /**
     * 获取所有
     * @param releaseInfo
     * @return
     */
    List<ReleaseInfo> selectList(ReleaseInfo releaseInfo);

    /**
     * 获取单个
     * @param releaseInfo
     * @return
     */
    ReleaseInfo selectOne(ReleaseInfo releaseInfo);

    /**
     * 获取已选设备组
     * @param releaseInfo
     * @return
     */
    List<ServerGroup> querySelectGroup(ReleaseInfo releaseInfo);
 }
