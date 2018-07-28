package model.superpermission;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperPermission")
public class SuperPermission extends BaseModel {

    /**
     * 主键
     */
    private String id;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 权限名称
     */
    private String permission;
    /**
     * 权限类型
     */
    private String type;
    /**
     * 父权限
     */
    private String parent;

    private boolean checked;

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getType() {
        return this.type;
    }

    public String getParent() {
        return this.parent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
