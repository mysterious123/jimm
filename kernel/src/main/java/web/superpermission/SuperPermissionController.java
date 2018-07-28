package web.superpermission;

import model.superpermission.SuperPermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superpermission.SuperPermissionService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superPermission")
public class SuperPermissionController {
    @Resource
    SuperPermissionService superPermissionService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperPermission superPermission) {
        return superPermissionService.insert(superPermission);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperPermission superPermission) {
        return superPermissionService.update(superPermission);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperPermission superPermission) {
        return superPermissionService.delete(superPermission);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperPermission superPermission) {
        superPermission.setPaging(false);
        return superPermissionService.selectList(superPermission);
    }
}
