package service.zmiboid;

import model.zmiboid.ZMibOid;
import java.util.List;

public interface ZMibOidService {
    /**
     * 新增
     * @param zMibOid
     * @return
     */
    int insert(ZMibOid zMibOid);

    /**
     * 删除
     * @param zMibOid
     * @return
     */
    int delete(ZMibOid zMibOid);

    /**
     * 更新
     * @param zMibOid
     * @return
     */
    int update(ZMibOid zMibOid);

    /**
     * 获取所有
     * @param zMibOid
     * @return
     */
    List<ZMibOid> selectList(ZMibOid zMibOid);

    /**
     * 获取单个
     * @param zMibOid
     * @return
     */
    ZMibOid selectOne(ZMibOid zMibOid);
}
