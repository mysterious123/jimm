package service.releasehistory;

import model.releasehistory.ReleaseHistory;
import java.util.List;

public interface ReleaseHistoryService {
    /**
     * 新增
     * @param releaseHistory
     * @return
     */
    int insert(ReleaseHistory releaseHistory);

    /**
     * 删除
     * @param releaseHistory
     * @return
     */
    int delete(ReleaseHistory releaseHistory);

    /**
     * 更新
     * @param releaseHistory
     * @return
     */
    int update(ReleaseHistory releaseHistory);

    /**
     * 获取所有
     * @param releaseHistory
     * @return
     */
    List<ReleaseHistory> selectList(ReleaseHistory releaseHistory);

    /**
     * 获取单个
     * @param releaseHistory
     * @return
     */
    ReleaseHistory selectOne(ReleaseHistory releaseHistory);
}
