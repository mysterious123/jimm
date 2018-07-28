package web.superdatasource;

import model.superdatasource.SuperDatasource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.superdatasource.SuperDatasourceService;
import javax.annotation.Resource;
import java.sql.SQLException;

@Controller
@RequestMapping("/superDatasource")
public class SuperDatasourceController {
    @Resource
    SuperDatasourceService superDatasourceService;

    @PostMapping
    @ResponseBody
    public Object insert(@RequestBody SuperDatasource superDatasource) {
        return superDatasourceService.insert(superDatasource);
    }

    @PutMapping
    @ResponseBody
    public Object update(@RequestBody SuperDatasource superDatasource) {
        return superDatasourceService.update(superDatasource);
    }

    @DeleteMapping
    @ResponseBody
    public Object delete(SuperDatasource superDatasource) {
        return superDatasourceService.delete(superDatasource);
    }

    @GetMapping
    @ResponseBody
    public Object selectList(SuperDatasource superDatasource) {
        superDatasource.setPaging(false);
        return superDatasourceService.selectList(superDatasource);
    }
    @PutMapping("connectTest")
    @ResponseBody
    public Object connectTest(@RequestBody SuperDatasource superDatasource) throws SQLException, ClassNotFoundException {
        return superDatasourceService.connectTest(superDatasource);
    }
}
