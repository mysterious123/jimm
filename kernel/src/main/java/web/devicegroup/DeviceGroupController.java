package web.devicegroup;

import model.devicegroup.DeviceGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.devicegroup.DeviceGroupService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/devicegroup")
@ApiMetadata(desc = "网络设备组管理", permission = "devicegroup")
public class DeviceGroupController {
    @Resource
    DeviceGroupService deviceGroupService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody DeviceGroup deviceGroup) {
        return deviceGroupService.insert(deviceGroup);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody DeviceGroup deviceGroup) {
        return deviceGroupService.update(deviceGroup);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(DeviceGroup deviceGroup) {
        return deviceGroupService.delete(deviceGroup);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(DeviceGroup deviceGroup) {
        deviceGroup.setPaging(false);
        return deviceGroupService.selectList(deviceGroup);
    }
}
