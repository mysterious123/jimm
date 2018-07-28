package service.zloginfo;

import model.zloginfo.ZLogInfo;
import java.util.List;

public interface ZLogInfoService {
    /**
     * 新增
     * @param zLogInfo
     * @return
     */
    int insert(ZLogInfo zLogInfo);

    /**
     * 删除
     * @param zLogInfo
     * @return
     */
    int delete(ZLogInfo zLogInfo);

    /**
     * 更新
     * @param zLogInfo
     * @return
     */
    int update(ZLogInfo zLogInfo);

    /**
     * 获取所有
     * @param zLogInfo
     * @return
     */
    List<ZLogInfo> selectList(ZLogInfo zLogInfo);

    /**
     * 获取单个
     * @param zLogInfo
     * @return
     */
    ZLogInfo selectOne(ZLogInfo zLogInfo);

    /**
     * 获取数量
     * @param zLogInfo
     * @return
     */
    int queryCount(ZLogInfo zLogInfo);
}
