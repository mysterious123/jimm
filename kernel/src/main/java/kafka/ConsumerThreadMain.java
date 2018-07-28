package kafka;

import db.DataSourceContextHolder;
import model.jimdata.data.JimData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.jimdata.JimDataService;
import service.zmibinfo.ZMibInfoService;

/**
 * Created by Administrator on 2018/4/17/017.
 */

public class ConsumerThreadMain{

    @Autowired
    private ZMibInfoService zMibInfoService;

    @Autowired
    JimDataService jimDataService;

   @Scheduled(cron = "9 * * * * ?")
    public void runKafka() {

        System.out.println("onloadKafka ----");
        /*ZMibInfo z = new ZMibInfo();
        z.setPaging(false);
        DataSourceContextHolder.setDataSourceType("dataSource1");
        List<ZMibInfo> list = zMibInfoService.selectList(z);
        System.out.println("zMibinfoList : "+list);*/

        /*("datasource1");*/

        DataSourceContextHolder.setDbType("jim_data");
        JimData jim =  new JimData();
        jim.setPaging(false);
        jimDataService.createTable(jim);
       // System.out.println("jjjjjjjjjjjjjjjj" + jj);
        DataSourceContextHolder.setDbType("jim");

       /* String brokers = "10.1.49.63:9092";
        String groupId = "consumer1";
        String topic = "windows";

        int threadNumber = 3;


        ConsumerThread consumerThread = new ConsumerThread(brokers, groupId, topic,zMibInfoService,jimDataService);
        consumerThread.start(threadNumber);*/

    }

}
