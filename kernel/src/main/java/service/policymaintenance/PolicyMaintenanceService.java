package service.policymaintenance;

import model.policymaintenance.PolicyMaintenance;
import java.util.List;

public interface PolicyMaintenanceService {
    /**
     * 新增
     * @param policyMaintenance
     * @return
     */
    int insert(PolicyMaintenance policyMaintenance);

    /**
     * 删除
     * @param policyMaintenance
     * @return
     */
    int delete(PolicyMaintenance policyMaintenance);

    /**
     * 更新
     * @param policyMaintenance
     * @return
     */
    int update(PolicyMaintenance policyMaintenance);

    /**
     * 获取所有
     * @param policyMaintenance
     * @return
     */
    List<PolicyMaintenance> selectList(PolicyMaintenance policyMaintenance);

    /**
     * 获取单个
     * @param policyMaintenance
     * @return
     */
    PolicyMaintenance selectOne(PolicyMaintenance policyMaintenance);
}
