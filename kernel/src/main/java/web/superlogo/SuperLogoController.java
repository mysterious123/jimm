package web.superlogo;

import model.superlogo.SuperLogo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.superlogo.SuperLogoService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/superLogo")
public class SuperLogoController {
    @Resource
    SuperLogoService superLogoService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperLogo superLogo) {
        return superLogoService.insert(superLogo);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperLogo superLogo) {
        return superLogoService.update(superLogo);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperLogo superLogo) {
        return superLogoService.delete(superLogo);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperLogo superLogo) {
        superLogo.setPaging(false);
        return superLogoService.selectList(superLogo);
    }

    @ResponseBody
    @PostMapping("uploadLogo")
    public void uploadLogo(@RequestParam(value = "file") MultipartFile file) {
        superLogoService.logoUpload(file);
    }
}
