package model.operationpolicyinfo;

import model.operationpolicyparam.OperationPolicyParam;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("OperationPolicyInfo")
public class OperationPolicyInfo extends BaseModel {

    /**
     * 主键
     */
    private String id;
    /**
     * 策略名称
     */
    private String policyName;
    /**
     * 策略类型
     */
    private String policyType;
    /**
     * 属性
     */
    private String nature;
    /**
     * 创建人
     */
    private String createPerson;
    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 策略参数
     */
    private List<OperationPolicyParam> operationPolicyParams;

    public void setId(String id) {
        this.id = id;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return this.id;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public String getPolicyType() {
        return this.policyType;
    }

    public String getNature() {
        return this.nature;
    }

    public String getCreatePerson() {
        return this.createPerson;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public List<OperationPolicyParam> getOperationPolicyParams() {
        return operationPolicyParams;
    }

    public void setOperationPolicyParams(List<OperationPolicyParam> operationPolicyParams) {
        this.operationPolicyParams = operationPolicyParams;
    }
}
