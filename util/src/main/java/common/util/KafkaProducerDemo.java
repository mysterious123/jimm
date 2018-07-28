package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/11/011.
 */
public class KafkaProducerDemo {
    /*public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers","10.1.49.63:9092");
        prop.put("key.serializer", StringSerializer.class.getName());
        prop.put("value.serializer",StringSerializer.class.getName());

        //指定topic
        String topic = "test";
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(prop);
        producer.send(new ProducerRecord<String,String>(topic,"hello test1-----------"));

        producer.close();

    }*/

    public static void main(String[] args) {
       // Map<String,Object> map = new HashMap<String,Object>();

        String str = "Aug  1 15:54:42 localhost monitor: unit_id:1|item_id:05|item_name:声音检测|item_result:65.1db";
        //String regex = "^(\\D*)(\\d+)(.*)";
        //String time =  "([a-zA-Z]{3}\\s+\\d{1,2}\\s+.{8})";
        String ip1 =  "(([a-z]{9}))";
        String ip2 =  "(((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
        String monitor = "(monitor:.*\\s)";

        Pattern p = Pattern.compile(monitor);
        Matcher m = p.matcher(str);
            while(m.find()){
            System.out.println("Found value: " + m.group(1) );
            //System.out.println("Found value: " + m.group(2) );

        }

    }
}
