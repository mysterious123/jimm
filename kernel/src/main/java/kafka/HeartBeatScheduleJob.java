package kafka;

import common.util.KafkaUtil;
import model.kafkadata.HeartBeat;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import service.kafkadata.HeartBeatService;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xj on 2018/5/10.
 */
public class HeartBeatScheduleJob implements Job{
    private final Logger L = LoggerFactory.getLogger(this.getClass());


    static final int threadNums = 6;

    @Value("${kafka.bootstrap_servers_config}")
    String bootstrap_servers_config;
    @Value("${kafka.group_id_config}")
    String group_id_config;
    @Value("${kafka.group_id_config_beat}")
    String group_id_config_beat;
    @Value("${kafka.enable_auto_commit_config}")
    String enable_auto_commit_config;
    @Value("${kafka.auto_offset_reset_config}")
    String auto_offset_reset_config;
    @Value("${kafka.topic.heartbeat}")
    String topicHeartBeat;

    @Autowired
    HeartBeatService heartBeatService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        {
            Properties props = new Properties();
            props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
            props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group_id_config);
            props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit_config);
            props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset_config);

            final Consumer<Long, String> heartBeatConsumer = KafkaUtil.createConsumer(props, topicHeartBeat);
            ConsumerRecords<Long, String> consumerRecords;
            ExecutorService executor = Executors.newFixedThreadPool(threadNums);
            while (true) {
                consumerRecords = heartBeatConsumer.poll(1000);
                consumerRecords.forEach(record ->
                        executor.submit(new HeartBeatResultThread(record, heartBeatService))
                );
            }
        }
    }
}
