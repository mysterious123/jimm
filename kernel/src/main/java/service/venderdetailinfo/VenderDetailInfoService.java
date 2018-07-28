package service.venderdetailinfo;

import model.venderdetailinfo.VenderDetailInfo;
import java.util.List;

public interface VenderDetailInfoService {
    /**
     * 新增
     * @param venderDetailInfo
     * @return
     */
    int insert(VenderDetailInfo venderDetailInfo);

    /**
     * 删除
     * @param venderDetailInfo
     * @return
     */
    int delete(VenderDetailInfo venderDetailInfo);

    /**
     * 更新
     * @param venderDetailInfo
     * @return
     */
    int update(VenderDetailInfo venderDetailInfo);

    /**
     * 获取所有
     * @param venderDetailInfo
     * @return
     */
    List<VenderDetailInfo> selectList(VenderDetailInfo venderDetailInfo);

    /**
     * 获取单个
     * @param venderDetailInfo
     * @return
     */
    VenderDetailInfo selectOne(VenderDetailInfo venderDetailInfo);

    int checkObjectIdExist(VenderDetailInfo venderDetailInfo);
}
