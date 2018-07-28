package service.superseverity;

import model.superseverity.SuperSeverity;
import java.util.List;

public interface SuperSeverityService {
    /**
     * 新增
     * @param superSeverity
     * @return
     */
    int insert(SuperSeverity superSeverity);

    /**
     * 删除
     * @param superSeverity
     * @return
     */
    int delete(SuperSeverity superSeverity);

    /**
     * 更新
     * @param superSeverity
     * @return
     */
    int update(SuperSeverity superSeverity);

    /**
     * 获取所有
     * @param superSeverity
     * @return
     */
    List<SuperSeverity> selectList(SuperSeverity superSeverity);

    /**
     * 获取单个
     * @param superSeverity
     * @return
     */
    SuperSeverity selectOne(SuperSeverity superSeverity);
}
