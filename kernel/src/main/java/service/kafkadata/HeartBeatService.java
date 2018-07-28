package service.kafkadata;


import model.kafkadata.HeartBeat;

import java.util.List;

public interface HeartBeatService {
    /**
     * 新增
     * @param heartBeat
     * @return
     */
    int insert(HeartBeat heartBeat);

    /**
     * 删除
     * @param heartBeat
     * @return
     */
    int delete(HeartBeat heartBeat);

    /**
     * 更新
     * @param heartBeat
     * @return
     */
    int update(HeartBeat heartBeat);

    /**
     * 获取所有
     * @param heartBeat
     * @return
     */
    List<HeartBeat> selectList(HeartBeat heartBeat);

    /**
     * 获取单个
     * @param heartBeat
     * @return
     */
    HeartBeat selectOne(HeartBeat heartBeat);
}
