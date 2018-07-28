package web.zagentinfo;

import model.zagentcolumninfo.ZAgentColumnInfo;
import model.zagentinfo.ZAgentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.zagentcolumninfo.ZAgentColumnInfoService;
import service.zagentinfo.ZAgentInfoService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/zAgentInfo")
@ApiMetadata(desc = "策略管理Agent", permission = "zAgentInfo")
public class ZAgentInfoController {
    @Resource
    ZAgentInfoService zAgentInfoService;

    @Resource
    private ZAgentColumnInfoService zAgentColumnInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ZAgentInfo zAgentInfo) {
        return zAgentInfoService.insert(zAgentInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ZAgentInfo zAgentInfo) {

        zAgentInfoService.update(zAgentInfo);
        //更新时获取子项,然后创建新表
        ZAgentColumnInfo zAgentColumnInfo = new ZAgentColumnInfo();
        zAgentColumnInfo.setAgentId(zAgentInfo.getId());
        zAgentColumnInfo.setPaging(false);
        //子项集合
        List<ZAgentColumnInfo> cInfo = zAgentColumnInfoService.selectList(zAgentColumnInfo);
        zAgentInfo.setColumnInfoList(cInfo);
        //此时切换数据源
        //DataSourceContextHolder.setDbType(DataSourceContextHolder.DATA_SOURCE_B);
        int flag = zAgentInfoService.createTable(zAgentInfo);
        //DataSourceContextHolder.setDbType(DataSourceContextHolder.DATA_SOURCE_A);
        return flag;
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ZAgentInfo zAgentInfo) {
        return zAgentInfoService.delete(zAgentInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ZAgentInfo zAgentInfo) {
        zAgentInfo.setPaging(false);
        return zAgentInfoService.selectList(zAgentInfo);
    }

    @GetMapping("/queryCount")
    @ResponseBody
    public Object queryCount(ZAgentInfo zAgentInfo){
        return zAgentInfoService.queryCount(zAgentInfo);
    }
}
