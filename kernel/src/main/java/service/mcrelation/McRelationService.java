package service.mcrelation;

import model.mcrelation.McRelation;
import java.util.List;

public interface McRelationService {
    /**
     * 新增
     * @param mcRelation
     * @return
     */
    int insert(McRelation mcRelation);

    /**
     * 删除
     * @param mcRelation
     * @return
     */
    int delete(McRelation mcRelation);

    /**
     * 更新
     * @param mcRelation
     * @return
     */
    int update(McRelation mcRelation);

    /**
     * 获取所有
     * @param mcRelation
     * @return
     */
    List<McRelation> selectList(McRelation mcRelation);

    /**
     * 获取单个
     * @param mcRelation
     * @return
     */
    McRelation selectOne(McRelation mcRelation);
}
