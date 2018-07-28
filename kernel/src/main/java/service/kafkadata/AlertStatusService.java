package service.kafkadata;

import model.kafkadata.AlertStatus;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */
public interface AlertStatusService {
    int insert(AlertStatus alertStatus);

    List<AlertStatus> selectList(AlertStatus alertStatus);
}
