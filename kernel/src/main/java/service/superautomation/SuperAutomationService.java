package service.superautomation;

import model.superautomation.SuperAutomation;
import java.util.List;

public interface SuperAutomationService {
    /**
     * 新增
     * @param superAutomation
     * @return
     */
    int insert(SuperAutomation superAutomation);

    /**
     * 删除
     * @param superAutomation
     * @return
     */
    int delete(SuperAutomation superAutomation);

    /**
     * 更新
     * @param superAutomation
     * @return
     */
    int update(SuperAutomation superAutomation);

    /**
     * 获取所有
     * @param superAutomation
     * @return
     */
    List<SuperAutomation> selectList(SuperAutomation superAutomation);

    /**
     * 获取单个
     * @param superAutomation
     * @return
     */
    SuperAutomation selectOne(SuperAutomation superAutomation);
}
