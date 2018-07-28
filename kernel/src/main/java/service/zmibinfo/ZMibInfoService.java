package service.zmibinfo;

import model.zmibinfo.ZMibInfo;
import java.util.List;

public interface ZMibInfoService {
    /**
     * 新增
     * @param zMibInfo
     * @return
     */
    int insert(ZMibInfo zMibInfo);

    /**
     * 删除
     * @param zMibInfo
     * @return
     */
    int delete(ZMibInfo zMibInfo);

    /**
     * 更新
     * @param zMibInfo
     * @return
     */
    int update(ZMibInfo zMibInfo);

    /**
     * 获取所有
     * @param zMibInfo
     * @return
     */
    List<ZMibInfo> selectList(ZMibInfo zMibInfo);

    /**
     * 获取单个
     * @param zMibInfo
     * @return
     */
    ZMibInfo selectOne(ZMibInfo zMibInfo);

    /**
     * 获取数量
     * @param zMibInfo
     * @return
     */
    int queryCount(ZMibInfo zMibInfo);


}
