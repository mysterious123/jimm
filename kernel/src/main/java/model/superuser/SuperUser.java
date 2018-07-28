package model.superuser;

import model.supermenu.SuperMenu;
import model.superpermission.SuperPermission;
import model.superrole.SuperRole;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("SuperUser")
public class SuperUser extends BaseModel {

    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 所属组
     */
    private String groupId;
    /**
     * 创建时间
     */
    private Long createTime;

    private String groupName;

    private List<SuperPermission> superPermissions;

    private List<SuperRole> superRoles;

    private List<SuperMenu> superMenus;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<SuperPermission> getSuperPermissions() {
        return superPermissions;
    }

    public void setSuperPermissions(List<SuperPermission> superPermissions) {
        this.superPermissions = superPermissions;
    }

    public List<SuperRole> getSuperRoles() {
        return superRoles;
    }

    public void setSuperRoles(List<SuperRole> superRoles) {
        this.superRoles = superRoles;
    }

    public List<SuperMenu> getSuperMenus() {
        return superMenus;
    }

    public void setSuperMenus(List<SuperMenu> superMenus) {
        this.superMenus = superMenus;
    }
}
