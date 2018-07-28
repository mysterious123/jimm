package web.policyperiod;

import model.policyperiod.PolicyPeriod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.policyperiod.PolicyPeriodService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/policyPeriod")
@ApiMetadata(desc = "监控时段管理", permission = "policyPeriod")
public class PolicyPeriodController {
    @Resource
    PolicyPeriodService policyPeriodService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody PolicyPeriod policyPeriod) {
        return policyPeriodService.insert(policyPeriod);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody PolicyPeriod policyPeriod) {
        return policyPeriodService.update(policyPeriod);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(PolicyPeriod policyPeriod) {
        return policyPeriodService.delete(policyPeriod);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(PolicyPeriod policyPeriod) {
        policyPeriod.setPaging(false);
        return policyPeriodService.selectList(policyPeriod);
    }
}
