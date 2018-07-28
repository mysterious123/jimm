package web.mcrelation;

import model.mcrelation.McRelation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.mcrelation.McRelationService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/mcRelation")
public class McRelationController {
    @Resource
    McRelationService mcRelationService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody McRelation mcRelation) {
        return mcRelationService.insert(mcRelation);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody McRelation mcRelation) {
        return mcRelationService.update(mcRelation);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(McRelation mcRelation) {
        return mcRelationService.delete(mcRelation);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(McRelation mcRelation) {
        mcRelation.setPaging(false);
        return mcRelationService.selectList(mcRelation);
    }
}
