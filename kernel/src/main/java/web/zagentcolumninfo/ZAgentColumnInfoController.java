package web.zagentcolumninfo;

import model.zagentcolumninfo.ZAgentColumnInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.zagentcolumninfo.ZAgentColumnInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/zAgentColumnInfo")
public class ZAgentColumnInfoController {
    @Resource
    ZAgentColumnInfoService zAgentColumnInfoService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody ZAgentColumnInfo zAgentColumnInfo) {
        return zAgentColumnInfoService.insert(zAgentColumnInfo);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody ZAgentColumnInfo zAgentColumnInfo) {
        return zAgentColumnInfoService.update(zAgentColumnInfo);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(ZAgentColumnInfo zAgentColumnInfo) {
        return zAgentColumnInfoService.delete(zAgentColumnInfo);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(ZAgentColumnInfo zAgentColumnInfo) {
        zAgentColumnInfo.setPaging(false);
        return zAgentColumnInfoService.selectList(zAgentColumnInfo);
    }
}
