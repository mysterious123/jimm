package kafka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.kafkadata.HeartBeat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import service.kafkadata.HeartBeatService;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 * 订阅心跳子线程，读取kafka心跳结果，往心跳表插入
 */
public class HeartBeatResultThread implements Runnable{
    private final Logger L = LoggerFactory.getLogger(this.getClass());

    private ConsumerRecord<Long, String> record;
    private HeartBeatService heartBeatService;

    public HeartBeatResultThread(ConsumerRecord<Long, String> record, HeartBeatService heartBeatService){
        this.record = record;
        this.heartBeatService = heartBeatService;
    }
    @Override
    public void run() {
        L.info("接收到heartbeat消息:" + record.value());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(record.value().trim());
        JsonObject recordJSON = jsonElement.getAsJsonObject();
        String host = recordJSON.get("host").getAsString();
        String timestamp = recordJSON.get("timestamp").getAsString();
        HeartBeat heartBeat = new HeartBeat();
        heartBeat.setHbHost(host);
        heartBeat.setPaging(false);
        List<HeartBeat> heartBeatList = heartBeatService.selectList(heartBeat);

        heartBeat.setHbClock(String.valueOf(DateTime.parse(timestamp).getMillis()));
        heartBeat.setHbTimestamp(timestamp);
        if(heartBeatList != null && !heartBeatList.isEmpty()){
            heartBeatService.update(heartBeat);
        }else{
            heartBeat.setHbMisfire("0");
            saveHeartBeat(heartBeat);
        }
    }

    public void saveHeartBeat(HeartBeat heartBeat){
        heartBeatService.insert(heartBeat);
    }
}
