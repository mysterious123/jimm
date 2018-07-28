package web.portgroup;

import model.portgroup.PortGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.portgroup.PortGroupService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/portgroup")
@ApiMetadata(desc = "端口组管理", permission = "portgroup")
public class PortGroupController {
    @Resource
    PortGroupService portGroupService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody PortGroup portGroup) {
        return portGroupService.insert(portGroup);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody PortGroup portGroup) {
        return portGroupService.update(portGroup);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(PortGroup portGroup) {
        return portGroupService.delete(portGroup);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(PortGroup portGroup) {
        portGroup.setPaging(false);
        return portGroupService.selectList(portGroup);
    }
}
