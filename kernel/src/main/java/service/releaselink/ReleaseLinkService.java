package service.releaselink;

import model.releaselink.ReleaseLink;
import java.util.List;

public interface ReleaseLinkService {
    /**
     * 新增
     * @param releaseLink
     * @return
     */
    int insert(ReleaseLink releaseLink);

    /**
     * 删除
     * @param releaseLink
     * @return
     */
    int delete(ReleaseLink releaseLink);

    /**
     * 更新
     * @param releaseLink
     * @return
     */
    int update(ReleaseLink releaseLink);

    /**
     * 获取所有
     * @param releaseLink
     * @return
     */
    List<ReleaseLink> selectList(ReleaseLink releaseLink);

    /**
     * 获取单个
     * @param releaseLink
     * @return
     */
    ReleaseLink selectOne(ReleaseLink releaseLink);
}
