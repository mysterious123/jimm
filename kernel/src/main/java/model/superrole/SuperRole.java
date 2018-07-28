package model.superrole;

import model.supermenu.SuperMenu;
import model.superpermission.SuperPermission;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("SuperRole")
public class SuperRole extends BaseModel {

    /**
     * 主键
     */
    private String id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Long createTime;

    private List<SuperMenu> superMenus;

    private List<SuperPermission> superPermissions;

    public void setId(String id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return this.id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public List<SuperMenu> getSuperMenus() {
        return superMenus;
    }

    public void setSuperMenus(List<SuperMenu> superMenus) {
        this.superMenus = superMenus;
    }

    public List<SuperPermission> getSuperPermissions() {
        return superPermissions;
    }

    public void setSuperPermissions(List<SuperPermission> superPermissions) {
        this.superPermissions = superPermissions;
    }
}
