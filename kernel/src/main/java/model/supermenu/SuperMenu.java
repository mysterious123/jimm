package model.supermenu;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("SuperMenu")
public class SuperMenu extends BaseModel {

    /**
     * 主键
     */
    private String id;
    /**
     *
     */
    private String text;
    /**
     *
     */
    private String icon;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String parent;


    private boolean checked;

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
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
