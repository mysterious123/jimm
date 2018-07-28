import model.kafkadata.AlertStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.kafkadata.AlertStatusService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public class SybaseController {
    @Resource
    AlertStatusService alertStatusService;
    @Test
    public void testDemo(){
//        AlertStatus alertStatus = new AlertStatus();
//        alertStatus.setPaging(false);
//        List<AlertStatus> alertStatusList = alertStatusService.selectList(alertStatus);
//        System.out.println(alertStatusList.size());
        List<Integer>list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(str ->{
            System.out.println(str);
        });

    }
}
