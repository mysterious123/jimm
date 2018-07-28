package kafka;

import model.zmibinfo.ZMibInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import service.jimdata.JimDataService;
import service.zmibinfo.ZMibInfoService;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/4/17/017.
 */
public class ConsumerThread {
    private final KafkaConsumer<String, String> consumer;
    private final String topic;
    // Threadpool of consumers
    private ExecutorService executor;

    ZMibInfoService zMibInfoService;

    JimDataService jimDataService;


    public ConsumerThread(String brokers, String groupId, String topic, ZMibInfoService zMibInfoService, JimDataService jimDataService){
        Properties properties = buildKafkaProperty(brokers,groupId);
        this.consumer = new KafkaConsumer<>(properties);
        this.topic = topic;
        this.consumer.subscribe(Arrays.asList(this.topic));
        this.zMibInfoService = zMibInfoService;
        this.jimDataService = jimDataService;
    }

    public void start(int threadNumber){
        executor = new ThreadPoolExecutor(threadNumber,threadNumber,0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        while (true){
            ConsumerRecords<String,String> consumerRecords = consumer.poll(100);
            for (ConsumerRecord<String,String> item : consumerRecords){
                //获取正则
                List<ZMibInfo> list = getList();
                executor.submit(new ConsumerThreadHandler(item,list,jimDataService));
            }
        }
    }

    private static Properties buildKafkaProperty(String brokers, String groupId){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.setProperty("group.id", groupId);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.offset.reset", "latest");   //earliest
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public List<ZMibInfo> getList(){
        //取出正则匹配
        ZMibInfo z = new ZMibInfo();
        z.setPaging(false);
        List<ZMibInfo> zMibInfoList = zMibInfoService.selectList(z);
        System.out.println("zMibInfoList -----" + zMibInfoList);

        return zMibInfoList;
    }
}
