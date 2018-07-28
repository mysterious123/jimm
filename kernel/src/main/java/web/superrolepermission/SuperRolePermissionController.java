package web.superrolepermission;

import model.superrolepermission.SuperRolePermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superrolepermission.SuperRolePermissionService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superRolePermission")
public class SuperRolePermissionController {
    @Resource
    SuperRolePermissionService superRolePermissionService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperRolePermission superRolePermission) {
        return superRolePermissionService.insert(superRolePermission);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperRolePermission superRolePermission) {
        return superRolePermissionService.update(superRolePermission);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperRolePermission superRolePermission) {
        return superRolePermissionService.delete(superRolePermission);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperRolePermission superRolePermission) {
        superRolePermission.setPaging(false);
        return superRolePermissionService.selectList(superRolePermission);
    }

}
