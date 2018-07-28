package web.managegroupinfo;

import model.managegroupinfo.ManageGroupInfo;
import model.userinfo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.managegroupinfo.ManageGroupInfoService;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/manageGroupInfo")
@ApiMetadata(desc = "管理组", permission = "manageGroupInfo")
public class ManageGroupInfoController {
    @Resource
    ManageGroupInfoService manageGroupInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody ManageGroupInfo manageGroupInfo) {
        return manageGroupInfoService.insert(manageGroupInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody ManageGroupInfo manageGroupInfo) {
        return manageGroupInfoService.update(manageGroupInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(ManageGroupInfo manageGroupInfo) {
        return manageGroupInfoService.delete(manageGroupInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setPaging(false);
        return manageGroupInfoService.selectList(manageGroupInfo);
    }

    @GetMapping("/querySelectUser")
    @ResponseBody
    public List<UserInfo> querySelectUser(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setPaging(false);
        return manageGroupInfoService.querySelectUser(manageGroupInfo);
    }

    @GetMapping("/queryNoSelectUser")
    @ResponseBody
    public List<UserInfo> queryNoSelectUser(ManageGroupInfo manageGroupInfo) {
        manageGroupInfo.setPaging(false);
        return manageGroupInfoService.queryNoSelectUser(manageGroupInfo);
    }
}
