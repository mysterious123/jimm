package web.serverinfo;

import model.serverinfo.ServerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.serverinfo.ServerInfoService;
import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/serverinfo")
@ApiMetadata(desc = "服务器管理", permission = "serverInfo")
public class ServerInfoController {
    @Resource
    ServerInfoService serverInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ServerInfo serverInfo) {
        return serverInfoService.insert(serverInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ServerInfo serverInfo) {
        return serverInfoService.update(serverInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ServerInfo serverInfo) {
        return serverInfoService.delete(serverInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ServerInfo serverInfo) {
        serverInfo.setPaging(false);
        return serverInfoService.selectList(serverInfo);
    }


    @GetMapping("/checkIpExist")
    @ResponseBody
    public Object checkIpExist(ServerInfo serverInfo) {
        return serverInfoService.checkIpExist(serverInfo);
    }

    @GetMapping("/getServerByGroupId")
    @ResponseBody
    public Object getServerByGroupId(ServerInfo serverInfo) {
        serverInfo.setPaging(false);
        return serverInfoService.getServerByGroupId(serverInfo);
    }
}
