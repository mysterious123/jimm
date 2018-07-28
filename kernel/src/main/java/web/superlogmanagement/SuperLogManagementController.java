package web.superlogmanagement;

import model.superlogmanagement.SuperLogManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superlogmanagement.SuperLogManagementService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superLogManagement")
public class SuperLogManagementController {
    @Resource
    SuperLogManagementService superLogManagementService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperLogManagement superLogManagement) {
        return superLogManagementService.insert(superLogManagement);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperLogManagement superLogManagement) {
        return superLogManagementService.update(superLogManagement);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperLogManagement superLogManagement) {
        return superLogManagementService.delete(superLogManagement);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperLogManagement superLogManagement) {
        superLogManagement.setPaging(false);
        return superLogManagementService.selectList(superLogManagement);
    }
    @GetMapping("/queryLogManagement")
    @ResponseBody
    public Object queryLogManagement(SuperLogManagement superLogManagement) {
        superLogManagement.setPaging(false);
        return superLogManagementService.queryLogManagement(superLogManagement);
    }
}
