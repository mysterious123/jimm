package web.superusergroup;

import model.managegroupinfo.ManageGroupInfo;
import model.superuser.SuperUser;
import model.superusergroup.SuperUserGroup;
import model.userinfo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superusergroup.SuperUserGroupService;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/superUserGroup")
public class SuperUserGroupController {
    @Resource
    SuperUserGroupService superUserGroupService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperUserGroup superUserGroup) {
        return superUserGroupService.insert(superUserGroup);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperUserGroup superUserGroup) {
        return superUserGroupService.update(superUserGroup);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperUserGroup superUserGroup) {
        return superUserGroupService.delete(superUserGroup);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperUserGroup superUserGroup) {
        superUserGroup.setPaging(false);
        return superUserGroupService.selectList(superUserGroup);
    }
    @GetMapping("/querySelectUser")
    @ResponseBody
    public List<SuperUser> querySelectUser(SuperUserGroup superUserGroup) {
        superUserGroup.setPaging(false);
        return superUserGroupService.querySelectUser(superUserGroup);
    }

    @GetMapping("/queryNoSelectUser")
    @ResponseBody
    public List<SuperUser> queryNoSelectUser(SuperUserGroup superUserGroup) {
        superUserGroup.setPaging(false);
        return superUserGroupService.queryNoSelectUser(superUserGroup);
    }
}
