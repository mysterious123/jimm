package service.operationpolicyinfo;

import model.operationpolicyinfo.OperationPolicyInfo;
import java.util.List;

public interface OperationPolicyInfoService {
    /**
     * 新增
     * @param operationPolicyInfo
     * @return
     */
    int insert(OperationPolicyInfo operationPolicyInfo);

    /**
     * 删除
     * @param operationPolicyInfo
     * @return
     */
    int delete(OperationPolicyInfo operationPolicyInfo);

    /**
     * 更新
     * @param operationPolicyInfo
     * @return
     */
    int update(OperationPolicyInfo operationPolicyInfo);

    /**
     * 获取所有
     * @param operationPolicyInfo
     * @return
     */
    List<OperationPolicyInfo> selectList(OperationPolicyInfo operationPolicyInfo);

    /**
     * 获取单个
     * @param operationPolicyInfo
     * @return
     */
    OperationPolicyInfo selectOne(OperationPolicyInfo operationPolicyInfo);
}
