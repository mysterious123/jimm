package service.communicationlinegroup;

import model.communicationlinegroup.CommunicationLineGroup;

import java.util.List;

public interface CommunicationLineGroupService {
    /**
     * 新增
     * @param CommunicationLineGroup
     * @return
     */
    int insert(CommunicationLineGroup CommunicationLineGroup);

    /**
     * 删除
     * @param CommunicationLineGroup
     * @return
     */
    int delete(CommunicationLineGroup CommunicationLineGroup);

    /**
     * 更新
     * @param CommunicationLineGroup
     * @return
     */
    int update(CommunicationLineGroup CommunicationLineGroup);

    /**
     * 获取所有
     * @param CommunicationLineGroup
     * @return
     */
    List<CommunicationLineGroup> selectList(CommunicationLineGroup CommunicationLineGroup);

    /**
     * 获取单个
     * @param CommunicationLineGroup
     * @return
     */
    CommunicationLineGroup selectOne(CommunicationLineGroup CommunicationLineGroup);
}
