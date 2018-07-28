package web.releasehistory;

import model.releasehistory.ReleaseHistory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.releasehistory.ReleaseHistoryService;
import javax.annotation.Resource;

@Controller
@RequestMapping("/releaseHistory")
public class ReleaseHistoryController {
    @Resource
    ReleaseHistoryService releaseHistoryService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody ReleaseHistory releaseHistory) {
        System.out.println("releaseHistoryaa:"+releaseHistory.getReleasePerson());
        return releaseHistoryService.insert(releaseHistory);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody ReleaseHistory releaseHistory) {
        return releaseHistoryService.update(releaseHistory);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(ReleaseHistory releaseHistory) {
        return releaseHistoryService.delete(releaseHistory);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(ReleaseHistory releaseHistory) {
        releaseHistory.setPaging(false);
        return releaseHistoryService.selectList(releaseHistory);
    }
}
