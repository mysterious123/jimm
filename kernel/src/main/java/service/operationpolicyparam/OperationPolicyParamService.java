package service.operationpolicyparam;

import model.operationpolicyparam.OperationPolicyParam;
import java.util.List;

public interface OperationPolicyParamService {
    /**
     * 新增
     * @param operationPolicyParam
     * @return
     */
    int insert(OperationPolicyParam operationPolicyParam);

    /**
     * 删除
     * @param operationPolicyParam
     * @return
     */
    int delete(OperationPolicyParam operationPolicyParam);

    /**
     * 更新
     * @param operationPolicyParam
     * @return
     */
    int update(OperationPolicyParam operationPolicyParam);

    /**
     * 获取所有
     * @param operationPolicyParam
     * @return
     */
    List<OperationPolicyParam> selectList(OperationPolicyParam operationPolicyParam);

    /**
     * 获取单个
     * @param operationPolicyParam
     * @return
     */
    OperationPolicyParam selectOne(OperationPolicyParam operationPolicyParam);

}
