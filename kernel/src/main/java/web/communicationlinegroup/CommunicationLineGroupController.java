package web.communicationlinegroup;

import model.communicationlinegroup.CommunicationLineGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.communicationlinegroup.CommunicationLineGroupService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/communicationLineGroup")
@ApiMetadata(desc = "线路组管理", permission = "communicationLineGroup")
public class CommunicationLineGroupController {
    @Resource
    CommunicationLineGroupService CommunicationLineGroupService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody CommunicationLineGroup CommunicationLineGroup) {
        return CommunicationLineGroupService.insert(CommunicationLineGroup);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody CommunicationLineGroup CommunicationLineGroup) {
        return CommunicationLineGroupService.update(CommunicationLineGroup);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(CommunicationLineGroup CommunicationLineGroup) {
        return CommunicationLineGroupService.delete(CommunicationLineGroup);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(CommunicationLineGroup CommunicationLineGroup) {
        CommunicationLineGroup.setPaging(false);
        return CommunicationLineGroupService.selectList(CommunicationLineGroup);
    }
}
