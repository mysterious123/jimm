package web.superrolemenu;

import model.superrolemenu.SuperRoleMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superrolemenu.SuperRoleMenuService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superRoleMenu")
public class SuperRoleMenuController {
    @Resource
    SuperRoleMenuService superRoleMenuService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperRoleMenu superRoleMenu) {
        return superRoleMenuService.insert(superRoleMenu);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperRoleMenu superRoleMenu) {
        return superRoleMenuService.update(superRoleMenu);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperRoleMenu superRoleMenu) {
        return superRoleMenuService.delete(superRoleMenu);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperRoleMenu superRoleMenu) {
        superRoleMenu.setPaging(false);
        return superRoleMenuService.selectList(superRoleMenu);
    }
}
