package web.operationpolicyparam;

import model.operationpolicyparam.OperationPolicyParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.operationpolicyparam.OperationPolicyParamService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/operationPolicyParam")
//@ApiMetadata(desc = "策略管理参数", permission = "operationPolicyParam")
public class OperationPolicyParamController {
    @Resource
    OperationPolicyParamService operationPolicyParamService;

    @PostMapping
    @ResponseBody
//    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody OperationPolicyParam operationPolicyParam) {
        return operationPolicyParamService.insert(operationPolicyParam);
    }

    @PutMapping
    @ResponseBody
//    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody OperationPolicyParam operationPolicyParam) {
        return operationPolicyParamService.update(operationPolicyParam);
    }

    @DeleteMapping
    @ResponseBody
//    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(OperationPolicyParam operationPolicyParam) {
        return operationPolicyParamService.delete(operationPolicyParam);
    }

    @GetMapping
    @ResponseBody
//    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(OperationPolicyParam operationPolicyParam) {
        operationPolicyParam.setPaging(false);
        return operationPolicyParamService.selectList(operationPolicyParam);
    }
}
