package web.venderinfo;

import model.venderinfo.VenderInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import secure.annotation.ApiMetadata;
import service.venderinfo.VenderInfoService;
import javax.annotation.Resource;
import javax.servlet.ServletContext;


@Controller
@RequestMapping("/venderInfo")
@ApiMetadata(desc = "设备库管理", permission = "venderInfo")
public class VenderInfoController implements ServletContextAware {
    private ServletContext context;
    @Resource
    VenderInfoService venderInfoService;
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
    @PostMapping
    @ResponseBody
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object insert(@RequestBody VenderInfo venderInfo) {
        return venderInfoService.insert(venderInfo);
    }

    @PutMapping
    @ResponseBody
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody VenderInfo venderInfo) {
        return venderInfoService.update(venderInfo);
    }

    @DeleteMapping
    @ResponseBody
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(VenderInfo venderInfo) {
        return venderInfoService.delete(venderInfo);
    }

    @GetMapping
    @ResponseBody
    @ApiMetadata(desc = "查询列表", permission = "selectList")
    public Object selectList(VenderInfo venderInfo) {
        venderInfo.setPaging(false);
        return venderInfoService.selectList(venderInfo);
    }
    @ResponseBody
    @PostMapping("uploadLogo")
    public void uploadLogo(@RequestParam(value = "file") MultipartFile file) {
        venderInfoService.logoUpload(file);
    }
}
