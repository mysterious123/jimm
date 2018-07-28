package service.superdatasource;

import model.superdatasource.SuperDatasource;

import java.sql.SQLException;
import java.util.List;

public interface SuperDatasourceService {
    /**
     * 新增
     * @param superDatasource
     * @return
     */
    int insert(SuperDatasource superDatasource);

    /**
     * 删除
     * @param superDatasource
     * @return
     */
    int delete(SuperDatasource superDatasource);

    /**
     * 更新
     * @param superDatasource
     * @return
     */
    int update(SuperDatasource superDatasource);

    /**
     * 获取所有
     * @param superDatasource
     * @return
     */
    List<SuperDatasource> selectList(SuperDatasource superDatasource);

    /**
     * 获取单个
     * @param superDatasource
     * @return
     */
    SuperDatasource selectOne(SuperDatasource superDatasource);

    /**
     * jdbc连接测试
     * @param superDatasource
     * @return
     */
    int connectTest(SuperDatasource superDatasource) throws ClassNotFoundException, SQLException;
}
