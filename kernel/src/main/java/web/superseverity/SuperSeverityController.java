package web.superseverity;

import model.superseverity.SuperSeverity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superseverity.SuperSeverityService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superSeverity")
public class SuperSeverityController {
    @Resource
    SuperSeverityService superSeverityService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperSeverity superSeverity) {
        return superSeverityService.insert(superSeverity);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperSeverity superSeverity) {
        return superSeverityService.update(superSeverity);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperSeverity superSeverity) {
        return superSeverityService.delete(superSeverity);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperSeverity superSeverity) {
        superSeverity.setPaging(false);
        return superSeverityService.selectList(superSeverity);
    }
}
