package web.managegroup;

import model.managegroup.ManageGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.managegroup.ManageGroupService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/manageGroup")
@ApiMetadata(desc = "管理组层级管理", permission = "manageGroup")
public class ManageGroupController {
    @Resource
    ManageGroupService manageGroupService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ManageGroup manageGroup) {
        return manageGroupService.insert(manageGroup);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ManageGroup manageGroup) {
        return manageGroupService.update(manageGroup);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ManageGroup manageGroup) {
        return manageGroupService.delete(manageGroup);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ManageGroup manageGroup) {
        manageGroup.setPaging(false);
        return manageGroupService.selectList(manageGroup);
    }
}
