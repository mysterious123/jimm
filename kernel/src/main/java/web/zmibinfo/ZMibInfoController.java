package web.zmibinfo;

import model.zmibinfo.ZMibInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.zmibinfo.ZMibInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/zMibInfo")
@ApiMetadata(desc = "策略管理MIB", permission = "zMibInfo")
public class ZMibInfoController {
    @Resource
    ZMibInfoService zMibInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ZMibInfo zMibInfo) {
        return zMibInfoService.insert(zMibInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ZMibInfo zMibInfo) {
        return zMibInfoService.update(zMibInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ZMibInfo zMibInfo) {
        return zMibInfoService.delete(zMibInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ZMibInfo zMibInfo) {
        zMibInfo.setPaging(false);
        return zMibInfoService.selectList(zMibInfo);
    }

    @GetMapping("/queryCount")
    @ResponseBody
    public Object queryCount(ZMibInfo zMibInfo){
        return zMibInfoService.queryCount(zMibInfo);
    }
}
