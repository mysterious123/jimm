package web.superautomation;

import model.superautomation.SuperAutomation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superautomation.SuperAutomationService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superAutomation")
public class SuperAutomationController {
    @Resource
    SuperAutomationService superAutomationService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperAutomation superAutomation) {
        return superAutomationService.insert(superAutomation);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperAutomation superAutomation) {
        return superAutomationService.update(superAutomation);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperAutomation superAutomation) {
        return superAutomationService.delete(superAutomation);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperAutomation superAutomation) {
        superAutomation.setPaging(false);
        return superAutomationService.selectList(superAutomation);
    }
}
