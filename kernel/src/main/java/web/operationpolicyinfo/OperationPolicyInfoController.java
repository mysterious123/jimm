package web.operationpolicyinfo;

import model.operationpolicyinfo.OperationPolicyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.operationpolicyinfo.OperationPolicyInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/operationPolicyInfo")
@ApiMetadata(desc = "策略管理", permission = "operationPolicyInfo")
public class OperationPolicyInfoController {
    @Resource
    OperationPolicyInfoService operationPolicyInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody OperationPolicyInfo operationPolicyInfo) {
        return operationPolicyInfoService.insert(operationPolicyInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody OperationPolicyInfo operationPolicyInfo) {
        return operationPolicyInfoService.update(operationPolicyInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(OperationPolicyInfo operationPolicyInfo) {
        return operationPolicyInfoService.delete(operationPolicyInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(OperationPolicyInfo operationPolicyInfo) {
        operationPolicyInfo.setPaging(false);
        return operationPolicyInfoService.selectList(operationPolicyInfo);
    }
}
