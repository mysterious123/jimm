package web.deviceinfo;

import model.deviceinfo.DeviceInfo;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.deviceinfo.DeviceInfoService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/deviceinfo")
@ApiMetadata(desc = "设备管理", permission = "deviceinfo")
public class DeviceInfoController {
    @Resource
    DeviceInfoService deviceInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody DeviceInfo deviceInfo) {
        return deviceInfoService.insert(deviceInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody DeviceInfo deviceInfo) {
        return deviceInfoService.update(deviceInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(DeviceInfo deviceInfo) {
        return deviceInfoService.delete(deviceInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(DeviceInfo deviceInfo) {
        deviceInfo.setPaging(false);
        return deviceInfoService.selectList(deviceInfo);
    }

    @PostMapping("/devicepoll")
    @ResponseBody
    public Object devicepoll(@RequestBody DeviceInfo deviceInfo){
        return deviceInfoService.snmpPoll(deviceInfo);
    }

   @RequestMapping("/checkIpExist")
   @ResponseBody
   public Object checkIpExist(DeviceInfo deviceInfo){
        return  deviceInfoService.checkIpExist(deviceInfo);
   }
    @GetMapping("/getDeviceByGroupId")
    @ResponseBody
    public Object getDeviceByGroupId(DeviceInfo deviceInfo) {
        deviceInfo.setPaging(false);
        return deviceInfoService.getDeviceByGroupId(deviceInfo);
    }
}
