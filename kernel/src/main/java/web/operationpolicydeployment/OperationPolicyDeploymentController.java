package web.operationpolicydeployment;

import model.operationpolicydeployment.OperationPolicyDeployment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.operationpolicydeployment.OperationPolicyDeploymentService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/operationPolicyDeployment")
public class OperationPolicyDeploymentController {
    @Resource
    OperationPolicyDeploymentService operationPolicyDeploymentService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody OperationPolicyDeployment operationPolicyDeployment) {
        return operationPolicyDeploymentService.insert(operationPolicyDeployment);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody OperationPolicyDeployment operationPolicyDeployment) {
        return operationPolicyDeploymentService.update(operationPolicyDeployment);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(OperationPolicyDeployment operationPolicyDeployment) {
        return operationPolicyDeploymentService.delete(operationPolicyDeployment);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setPaging(false);
        return operationPolicyDeploymentService.selectList(operationPolicyDeployment);
    }
    @GetMapping("/querySelectServerInfo")
    @ResponseBody
    public Object querySelectServerInfo(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setPaging(false);
        return operationPolicyDeploymentService.querySelectServerInfo(operationPolicyDeployment);
    }
    @GetMapping("/queryUnSelectServerInfo")
    @ResponseBody
    public Object queryUnSelectServerInfo(OperationPolicyDeployment operationPolicyDeployment) {
        operationPolicyDeployment.setPaging(false);
        return operationPolicyDeploymentService.queryUnSelectServerInfo(operationPolicyDeployment);
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    public Object batchInsert(@RequestBody OperationPolicyDeployment operationPolicyDeployment){
        return operationPolicyDeploymentService.batchInsert(operationPolicyDeployment);
    }
}
