package web.zloginfo;

import model.zloginfo.ZLogInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.zloginfo.ZLogInfoService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/zLogInfo")
@ApiMetadata(desc = "策略管理LOG", permission = "zLogInfo")
public class ZLogInfoController {
    @Resource
    ZLogInfoService zLogInfoService;


    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ZLogInfo zLogInfo) {
        return zLogInfoService.insert(zLogInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ZLogInfo zLogInfo) {
        return zLogInfoService.update(zLogInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ZLogInfo zLogInfo) {
        return zLogInfoService.delete(zLogInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ZLogInfo zLogInfo) {
        zLogInfo.setPaging(false);
        return zLogInfoService.selectList(zLogInfo);
    }

    @GetMapping("/queryCount")
    @ResponseBody
    public Object queryCount(ZLogInfo zLogInfo){
        return zLogInfoService.queryCount(zLogInfo);
    }

}
