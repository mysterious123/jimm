package service.policyperiod;

import model.policyperiod.PolicyPeriod;
import java.util.List;

public interface PolicyPeriodService {
    /**
     * 新增
     * @param policyPeriod
     * @return
     */
    int insert(PolicyPeriod policyPeriod);

    /**
     * 删除
     * @param policyPeriod
     * @return
     */
    int delete(PolicyPeriod policyPeriod);

    /**
     * 更新
     * @param policyPeriod
     * @return
     */
    int update(PolicyPeriod policyPeriod);

    /**
     * 获取所有
     * @param policyPeriod
     * @return
     */
    List<PolicyPeriod> selectList(PolicyPeriod policyPeriod);

    /**
     * 获取单个
     * @param policyPeriod
     * @return
     */
    PolicyPeriod selectOne(PolicyPeriod policyPeriod);
}
