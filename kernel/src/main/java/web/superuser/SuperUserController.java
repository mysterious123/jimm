package web.superuser;

import model.superuser.SuperUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superuser.SuperUserService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superUser")
public class SuperUserController {
    @Resource
    SuperUserService superUserService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperUser superUser) {
        return superUserService.insert(superUser);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperUser superUser) {
        return superUserService.update(superUser);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperUser superUser) {
        return superUserService.delete(superUser);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperUser superUser) {
        superUser.setPaging(false);
        return superUserService.selectList(superUser);
    }

    @GetMapping("/queryUserInfo")
    @ResponseBody
    public Object queryUserInfo(SuperUser superUser) {
        superUser.setPaging(false);
        return superUserService.queryUserInfo(superUser);
    }
}
