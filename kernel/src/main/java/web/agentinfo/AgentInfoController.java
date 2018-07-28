package web.agentinfo;

import model.agentinfo.AgentInfo;
import model.kafkadata.AlertStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import secure.annotation.ApiMetadata;
import service.agentinfo.AgentInfoService;
import service.kafkadata.AlertStatusService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/agentInfo")
@ApiMetadata(desc = "代理注册", permission = "agentInfo")
public class AgentInfoController {
    @Resource
    AgentInfoService agentInfoService;


    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody AgentInfo agentInfo) {
        return agentInfoService.insert(agentInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody AgentInfo agentInfo) {
        return agentInfoService.update(agentInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(AgentInfo agentInfo) {
        return agentInfoService.delete(agentInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(AgentInfo agentInfo) {
        agentInfo.setPaging(false);
        return agentInfoService.selectList(agentInfo);
    }
    @GetMapping("/queryTypeCounts")
    @ResponseBody
    public Object queryTypeCounts(AgentInfo agentInfo) {
        agentInfo.setPaging(false);
        return agentInfoService.queryTypeCounts(agentInfo);
    }
    @ResponseBody
    @PostMapping("uploadAgentFile")
    public void uploadAgentFile(@RequestParam(value = "file") MultipartFile file) {
        agentInfoService.uploadAgentFile(file);
    }

//    public static void main(String[] args){
//        AlertStatus alertStatus = new AlertStatus();
//        alertStatus.setPaging(false);
//        List<AlertStatus> alertStatusList = alertStatusService.selectList(alertStatus);
//        System.out.println(alertStatusList.size());
//    }
}
