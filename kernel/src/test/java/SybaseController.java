import dao.BaseMapper;
import model.kafkadata.AlertStatus;
import model.serverinfo.ServerInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.kafkadata.AlertStatusService;
import service.serverinfo.ServerInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public class SybaseController {
    @Resource
    AlertStatusService alertStatusService;

    @Resource
    ServerInfoService serverInfoService;

    @Resource
    private BaseMapper<ServerInfo> baseMapper;

    private static final String NAMESPACE = ServerInfo.class.getName();

    @Test
    public void selectOne() {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setServerIp("11.24.115.234");
        serverInfo.setStatmentId(NAMESPACE + ".selectOne");
       String  StringIp = baseMapper.selectOne(serverInfo).getServerIp();
       System.out.println(StringIp);
    }
    @Test
    public void testDemo(){
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setPaging(false);
        // serverInfo.setServerIp(host);
        serverInfo.setServerIp("11.24.115.231");
        List<ServerInfo> serverInfoList = serverInfoService.selectList(serverInfo);
        for ( ServerInfo   t :serverInfoList){
            System.out.println(t.getAssetId()+"kkkk5");
        }





//        AlertStatus alertStatus = new AlertStatus();
//        alertStatus.setPaging(false);
//
//        List<AlertStatus> alertStatusList = alertStatusService.selectList(alertStatus);
//        System.out.println(alertStatusList.size());
//        List<Integer>list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.forEach(str ->{
//            System.out.println(str);
//        });

    }
}
