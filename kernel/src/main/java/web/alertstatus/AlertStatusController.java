package web.alertstatus;

import model.kafkadata.AlertStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.kafkadata.AlertStatusService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/5/7.
 */
@Controller
@RequestMapping("/alertStatus")
public class AlertStatusController {
    @Resource
    AlertStatusService alertStatusService;

    @GetMapping
    @ResponseBody
    public Object selectList(AlertStatus alertStatus) {
        alertStatus.setPaging(false);
        return alertStatusService.selectList(alertStatus);
    }
}
