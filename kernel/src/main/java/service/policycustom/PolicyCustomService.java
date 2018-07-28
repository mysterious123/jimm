package service.policycustom;

import model.policycustom.PolicyCustom;
import java.util.List;

public interface PolicyCustomService {
    /**
     * 新增
     * @param policyCustom
     * @return
     */
    int insert(PolicyCustom policyCustom);

    /**
     * 删除
     * @param policyCustom
     * @return
     */
    int delete(PolicyCustom policyCustom);

    /**
     * 更新
     * @param policyCustom
     * @return
     */
    int update(PolicyCustom policyCustom);

    /**
     * 获取所有
     * @param policyCustom
     * @return
     */
    List<PolicyCustom> selectList(PolicyCustom policyCustom);

    /**
     * 获取单个
     * @param policyCustom
     * @return
     */
    PolicyCustom selectOne(PolicyCustom policyCustom);
}
