package common.util;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaUtil {
    public static Consumer<Long, String> createConsumer(Properties props, String topic) {
        //赋予默认配置
        if (props.getProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG) == null) {
            props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        }
        if (props.getProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG) == null) {
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        }
        if (props.getProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG) == null) {
            props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        }
        //创建consumer
        KafkaConsumer consumer = new KafkaConsumer(props);
        //订阅topic
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

    public static Producer<String, Object> createProducer(Properties props){
        return new KafkaProducer<>(props);
    }
}
