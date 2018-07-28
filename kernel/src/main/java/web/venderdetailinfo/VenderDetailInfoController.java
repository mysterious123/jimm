package web.venderdetailinfo;

import model.venderdetailinfo.VenderDetailInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.venderdetailinfo.VenderDetailInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/venderDetailInfo")
@ApiMetadata(desc = "设备库详情管理", permission = "venderDetailInfo")
public class VenderDetailInfoController {
    @Resource
    VenderDetailInfoService venderDetailInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody VenderDetailInfo venderDetailInfo) {
        return venderDetailInfoService.insert(venderDetailInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody VenderDetailInfo venderDetailInfo) {
        return venderDetailInfoService.update(venderDetailInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(VenderDetailInfo venderDetailInfo) {
        return venderDetailInfoService.delete(venderDetailInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(VenderDetailInfo venderDetailInfo) {
        venderDetailInfo.setPaging(false);
        return venderDetailInfoService.selectList(venderDetailInfo);
    }
    @GetMapping("/checkObjectIdExist")
    @ResponseBody
    public Object checkObjectIdExist(VenderDetailInfo venderDetailInfo) {
        return venderDetailInfoService.checkObjectIdExist(venderDetailInfo);
    }
}
