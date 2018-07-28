package web.policycustom;

import common.util.TimeUtil;
import model.policycustom.PolicyCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.policycustom.PolicyCustomService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/policyCustom")
@ApiMetadata(desc = "级别阈值微调", permission = "policyCustom")
public class PolicyCustomController {
    @Resource
    PolicyCustomService policyCustomService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody PolicyCustom policyCustom) {
        return policyCustomService.insert(policyCustom);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody PolicyCustom policyCustom) {
        return policyCustomService.update(policyCustom);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(PolicyCustom policyCustom) {
        return policyCustomService.delete(policyCustom);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(PolicyCustom policyCustom) {
        policyCustom.setPaging(false);
        return policyCustomService.selectList(policyCustom);
    }
}
