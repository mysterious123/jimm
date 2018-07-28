package web.communicationlineinfo;

import model.communicationlineinfo.CommunicationLineInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.communicationlineinfo.CommunicationLineInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/communicationLineInfo")
@ApiMetadata(desc = "线路管理", permission = "communicationLineInfo")
public class CommunicationLineInfoController {
    @Resource
    CommunicationLineInfoService communicationLineInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody CommunicationLineInfo communicationLineInfo) {
        return communicationLineInfoService.insert(communicationLineInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody CommunicationLineInfo communicationLineInfo) {
        return communicationLineInfoService.update(communicationLineInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(CommunicationLineInfo communicationLineInfo) {
        return communicationLineInfoService.delete(communicationLineInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setPaging(false);
        return communicationLineInfoService.selectList(communicationLineInfo);
    }
    @GetMapping("/checkLineNameExist")
    @ResponseBody
    public Object checkLineNameExist(CommunicationLineInfo communicationLineInfo){
        return communicationLineInfoService.checkLineNameExist(communicationLineInfo);
    }

    @GetMapping("/getLineByGroupId")
    @ResponseBody
    public Object getLineByGroupId(CommunicationLineInfo communicationLineInfo) {
        communicationLineInfo.setPaging(false);
        return communicationLineInfoService.getLineByGroupId(communicationLineInfo);
    }
}
