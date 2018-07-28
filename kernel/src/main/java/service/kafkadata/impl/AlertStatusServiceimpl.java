package service.kafkadata.impl;

import dao.BaseMapper;
import db.DataSource;
import db.DataSourceContextHolder;
import model.kafkadata.AlertStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.kafkadata.AlertStatusService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xj on 2018/5/4.
 */
@Service("AlertStatusService")
@DataSource(DataSourceContextHolder.DATA_SOURCE_C)
public class AlertStatusServiceimpl implements AlertStatusService{
    private final Logger L = LoggerFactory.getLogger(AlertStatusServiceimpl.class);
    private static final String NAMESPACE = AlertStatus.class.getName();
    @Resource
    private BaseMapper<AlertStatus> baseMapper;
    @Override
    public int insert(AlertStatus alertStatus) {
        alertStatus.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(alertStatus);
    }

    @Override
    public List<AlertStatus> selectList(AlertStatus alertStatus) {
        alertStatus.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(alertStatus);
    }



}
