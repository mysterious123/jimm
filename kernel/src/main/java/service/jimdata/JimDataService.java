package service.jimdata;

import model.jimdata.data.JimData;
import java.util.List;

public interface JimDataService {
    /**
     * 新增
     * @param jimData
     * @return
     */
    int insert(JimData jimData);

    /**
     * 删除
     * @param jimData
     * @return
     */
    int delete(JimData jimData);

    /**
     * 更新
     * @param jimData
     * @return
     */
    int update(JimData jimData);

    /**
     * 获取所有
     * @param jimData
     * @return
     */
    List<JimData> selectList(JimData jimData);

    /**
     * 获取单个
     * @param jimData
     * @return
     */
    JimData selectOne(JimData jimData);

    int createTable(JimData jimData);
}
