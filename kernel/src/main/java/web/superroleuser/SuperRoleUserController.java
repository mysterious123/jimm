package web.superroleuser;

import model.superroleuser.SuperRoleUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superroleuser.SuperRoleUserService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superRoleUser")
public class SuperRoleUserController {
    @Resource
    SuperRoleUserService superRoleUserService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperRoleUser superRoleUser) {
        return superRoleUserService.insert(superRoleUser);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperRoleUser superRoleUser) {
        return superRoleUserService.update(superRoleUser);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperRoleUser superRoleUser) {
        return superRoleUserService.delete(superRoleUser);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperRoleUser superRoleUser) {
        superRoleUser.setPaging(false);
        return superRoleUserService.selectList(superRoleUser);
    }
}
