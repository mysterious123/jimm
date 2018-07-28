package web.supermenu;

import model.supermenu.SuperMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.supermenu.SuperMenuService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superMenu")
public class SuperMenuController {
    @Resource
    SuperMenuService superMenuService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperMenu superMenu) {
        return superMenuService.insert(superMenu);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperMenu superMenu) {
        return superMenuService.update(superMenu);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperMenu superMenu) {
        return superMenuService.delete(superMenu);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperMenu superMenu) {
        superMenu.setPaging(false);
        return superMenuService.selectList(superMenu);
    }
}
