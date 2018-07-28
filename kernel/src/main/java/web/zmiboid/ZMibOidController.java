package web.zmiboid;

import model.zmiboid.ZMibOid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.zmiboid.ZMibOidService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/zMibOid")
public class ZMibOidController {
    @Resource
    ZMibOidService zMibOidService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody ZMibOid zMibOid) {
        return zMibOidService.insert(zMibOid);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody ZMibOid zMibOid) {
        return zMibOidService.update(zMibOid);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(ZMibOid zMibOid) {
        return zMibOidService.delete(zMibOid);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(ZMibOid zMibOid) {
        zMibOid.setPaging(false);
        return zMibOidService.selectList(zMibOid);
    }
}
