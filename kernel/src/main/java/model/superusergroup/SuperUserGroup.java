package model.superusergroup;

import model.superuser.SuperUser;
import model.superusergroupLink.SuperUserGroupLink;
import org.apache.ibatis.type.Alias;
import model.BaseModel;

import java.util.List;

@Alias("SuperUserGroup")
public class SuperUserGroup extends BaseModel {

    /**
     *主键
     */
    private String id;
    /**
     *用户组名称
     */
    private String groupName;
    /**
     *创建时间
     */
    private Long createTime;
    /**
     *角色名
     */
    private String roleId;

    private List<SuperUserGroupLink> superUserIds;


    public void setId(String id){
        this.id = id;
    }
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    public String getId(){
        return this.id;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRoleId(){
        return this.roleId;
    }

    public List<SuperUserGroupLink> getSuperUserIds() {
        return superUserIds;
    }

    public void setSuperUserIds(List<SuperUserGroupLink> superUserIds) {
        this.superUserIds = superUserIds;
    }
}
