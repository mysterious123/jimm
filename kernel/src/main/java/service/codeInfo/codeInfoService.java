package service.codeInfo;

import model.codeInfo.codeInfo;
import java.util.List;

public interface codeInfoService {
    /**
     *
     * 新增
     * @param codeInfo
     * @return
     */
    int insert(codeInfo codeInfo);

    /**
     * 删除
     * @param codeInfo
     * @return
     */
    int delete(codeInfo codeInfo);

    /**
     * 更新
     * @param codeInfo
     * @return
     */
    int update(codeInfo codeInfo);

    /**
     * 获取所有
     * @param codeInfo
     * @return
     */
    List<codeInfo> selectList(codeInfo codeInfo);

    /**
     * 获取单个
     * @param codeInfo
     * @return
     */
    codeInfo selectOne(codeInfo codeInfo);
}
