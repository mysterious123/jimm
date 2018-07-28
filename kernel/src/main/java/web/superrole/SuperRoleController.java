package web.superrole;

import model.superrole.SuperRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superrole.SuperRoleService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superRole")
public class SuperRoleController {
    @Resource
    SuperRoleService superRoleService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperRole superRole) {
        return superRoleService.insert(superRole);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperRole superRole) {
        return superRoleService.update(superRole);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperRole superRole) {
        return superRoleService.delete(superRole);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperRole superRole) {
        superRole.setPaging(false);
        return superRoleService.selectList(superRole);
    }

    @GetMapping("selectMenuByRoleId")
    @ResponseBody
    public Object selectMenuByRoleId(SuperRole superRole) {
        superRole.setPaging(false);
        return superRoleService.selectMenuByRoleId(superRole);
    }

    @GetMapping("selectPermissionByRoleId")
    @ResponseBody
    public Object selectPermissionByRoleId(SuperRole superRole) {
        superRole.setPaging(false);
        return superRoleService.selectPermissionByRoleId(superRole);
    }
}
