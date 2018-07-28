package service.kafkadata.impl;

import dao.BaseMapper;
import db.DataSource;
import db.DataSourceContextHolder;
import model.kafkadata.KafkaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.kafkadata.KafkaService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/28/028.
 */
@Service("KafkaService")
@DataSource(DataSourceContextHolder.DATA_SOURCE_B)
public class KafkaServiceimpl implements KafkaService {
    private final Logger L = LoggerFactory.getLogger(KafkaServiceimpl.class);
    private static final String NAMESPACE = KafkaServiceimpl.class.getName();

    @Resource
    private BaseMapper<KafkaData> baseMapper;

    @Override
    public List<String> selectColList(String tableName) {
        return baseMapper.getSqlSessionTemplate().selectList(NAMESPACE + ".selectCol",tableName);
    }

    @Override
    public int insert(KafkaData kafkaData) {
        kafkaData.setStatmentId(NAMESPACE + ".insert");
        return  baseMapper.insert(kafkaData);
    }
}
