package common.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;


/**
 * Created by Administrator on 2018/4/11/011.
 */
public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers","10.1.49.63:9092");
        prop.put("key.deserializer", StringDeserializer.class.getName());
        prop.put("value.deserializer",StringDeserializer.class.getName());
        prop.put("auto.offset.rest","earliest");
        prop.setProperty("group.id","consumer1");
        String topic = "test";

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        List<String> topics = new ArrayList<String>();
        topics.add(topic);
        consumer.subscribe(topics);

        final int minBatchSize = 1;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                for(ConsumerRecord<String, String> record : buffer){

                    String str  = record.value();
                    Map map = TemplateUtil.genObjFormJson(str,HashMap.class);
                    String message = (String)map.get("message");

                    String time = message.substring(0,15);

               }
                buffer.clear();
            }
        }
    }


}
