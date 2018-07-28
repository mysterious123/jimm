package web.portinfo;

import model.portinfo.PortInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.portinfo.PortInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/portinfo")
@ApiMetadata(desc = "端口信息管理", permission = "portinfo")
public class PortInfoController {
    @Resource
    PortInfoService portInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody PortInfo portInfo) {
        return portInfoService.insert(portInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody PortInfo portInfo) {
        return portInfoService.update(portInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(PortInfo portInfo) {
        return portInfoService.delete(portInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(PortInfo portInfo) {
        portInfo.setPaging(false);
        return portInfoService.selectList(portInfo);
    }
    @PostMapping("/copy")
    @ResponseBody
    @ApiMetadata(desc = "复制", permission = "copy")
    public Object copy(@RequestBody PortInfo portInfo) {
        return portInfoService.insert(portInfo);
    }

    @GetMapping("/checkPortIpExist")
    @ResponseBody
    public Object checkPortIpExist(PortInfo portInfo) {
        portInfo.setPaging(false);
        return portInfoService.checkPortIpExist(portInfo);
    }
    @GetMapping("/getPortByGroupId")
    @ResponseBody
    public Object getPortByGroupId(PortInfo portInfo) {
        portInfo.setPaging(false);
        return portInfoService.getPortByGroupId(portInfo);
    }
}
