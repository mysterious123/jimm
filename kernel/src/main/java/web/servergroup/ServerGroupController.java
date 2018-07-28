package web.servergroup;

import model.servergroup.ServerGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.servergroup.ServerGroupService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/servergroup")
@ApiMetadata(desc = "服务器组管理", permission = "servergroup")
public class ServerGroupController {
    @Resource
    ServerGroupService serverGroupService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ServerGroup serverGroup) {
        return serverGroupService.insert(serverGroup);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ServerGroup serverGroup) {
        return serverGroupService.update(serverGroup);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ServerGroup serverGroup) {
        return serverGroupService.delete(serverGroup);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ServerGroup serverGroup) {
        serverGroup.setPaging(false);
        return serverGroupService.selectList(serverGroup);
    }
    @GetMapping("level")
    @ResponseBody
    public Object selectLevel(ServerGroup serverGroup) {
        serverGroup.setPaging(false);
        return serverGroupService.selectLevel(serverGroup);
    }
}
