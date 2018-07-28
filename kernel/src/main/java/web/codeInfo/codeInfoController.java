package web.codeInfo;

import model.codeInfo.codeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.codeInfo.codeInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/codeInfo")
@ApiMetadata(desc = "数据字典", permission = "codeInfo")
public class codeInfoController {

    @Resource
    codeInfoService codeInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody codeInfo codeInfo) {
        return codeInfoService.insert(codeInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody codeInfo codeInfo) {
        return codeInfoService.update(codeInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(codeInfo codeInfo) {
        return codeInfoService.delete(codeInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(codeInfo codeInfo) {
        codeInfo.setPaging(false);
        return codeInfoService.selectList(codeInfo);
    }
}
