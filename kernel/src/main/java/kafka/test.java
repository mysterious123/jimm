package kafka;

import dao.BaseMapper;
import model.serverinfo.ServerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.serverinfo.ServerInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Created with IDEA
 * @author:LiWangZhou
 * @Date:2018/7/27/027
 * @Time:15:11
 **/
/**
 * Created by susq on 2017-5-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.properties"})
public class test {

       @Resource
        private BaseMapper<ServerInfo> serverInfo;

        @Test
        public void testSelect() {
            System.out.println("nihao");
            ServerInfo  ServerInfo= new ServerInfo();
            ServerInfo.setServerIp("11.24.115.232");
            List<ServerInfo >  userInfo = serverInfo.selectList( ServerInfo);
            for ( ServerInfo f   :userInfo) {
                System.out.println("liwangzhou"+f.getApplicationSystem());
            }
            }
//    @RunWith(SpringJUnit4ClassRunner.class)
//    @ContextConfiguration({"classpath:applicationContext.xml"})
//    public class ReadDaoImplTest {
//        @Resource ReadDao readDao;
//
//        @Test
//        public void getListTest(){
//            List<Client> clientList = readDao.getList("client.test", null);
//
//            for(Client c:clientList){
//                System.out.println(c.getVersionNum());
//            }
//        }


    }


