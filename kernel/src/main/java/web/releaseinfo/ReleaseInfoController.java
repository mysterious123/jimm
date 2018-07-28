package web.releaseinfo;

import model.releaseinfo.ReleaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.releaseinfo.ReleaseInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/releaseInfo")
@ApiMetadata(desc = "生效管理", permission = "releaseInfo")
public class ReleaseInfoController {
    @Resource
    ReleaseInfoService releaseInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ReleaseInfo releaseInfo) {
        return releaseInfoService.insert(releaseInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ReleaseInfo releaseInfo) {
        return releaseInfoService.update(releaseInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ReleaseInfo releaseInfo) {
        return releaseInfoService.delete(releaseInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ReleaseInfo releaseInfo) {
        releaseInfo.setPaging(false);
        return releaseInfoService.selectList(releaseInfo);
    }

    @GetMapping("/querySelectGroup")
    @ResponseBody
    public Object querySelectGroup(ReleaseInfo releaseInfo){
        releaseInfo.setPaging(false);
        return releaseInfoService.querySelectGroup(releaseInfo);
    }
}
