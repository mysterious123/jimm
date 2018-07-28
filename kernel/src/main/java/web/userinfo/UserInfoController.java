package web.userinfo;

import model.userinfo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secure.annotation.ApiMetadata;
import service.userinfo.UserInfoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/userinfo")
@ApiMetadata(desc = "用户信息管理", permission = "userinfo")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody UserInfo userInfo) {
        return userInfoService.insert(userInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody UserInfo userInfo) {
        return userInfoService.update(userInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(UserInfo userInfo) {
        return userInfoService.delete(userInfo);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(UserInfo userInfo) {
        userInfo.setPaging(false);
        return userInfoService.selectList(userInfo);
    }
    @GetMapping("/queryGroupName")
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "queryGroupName")
    public Object queryGroupName(UserInfo userInfo) {
        userInfo.setPaging(false);
        return userInfoService.queryGroupName(userInfo);
    }
}
