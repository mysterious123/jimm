package service.kafkadata;

import model.kafkadata.KafkaData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28/028.
 */
public interface KafkaService {

    //根据表名获取表字段
    List<String> selectColList(String tableName);

    int insert(KafkaData kafkaData);
}
