package web.policymaintenance;

import model.policymaintenance.PolicyMaintenance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.policymaintenance.PolicyMaintenanceService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/policyMaintenance")
@ApiMetadata(desc = "维护期管理", permission = "policyMaintenance")
public class PolicyMaintenanceController {
    @Resource
    PolicyMaintenanceService policyMaintenanceService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody PolicyMaintenance policyMaintenance) {
        return policyMaintenanceService.insert(policyMaintenance);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody PolicyMaintenance policyMaintenance) {
        return policyMaintenanceService.update(policyMaintenance);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(PolicyMaintenance policyMaintenance) {
        return policyMaintenanceService.delete(policyMaintenance);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(PolicyMaintenance policyMaintenance) {
        policyMaintenance.setPaging(false);
        return policyMaintenanceService.selectList(policyMaintenance);
    }
}
