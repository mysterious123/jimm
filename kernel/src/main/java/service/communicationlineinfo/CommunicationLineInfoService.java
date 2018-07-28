package service.communicationlineinfo;

import model.communicationlineinfo.CommunicationLineInfo;

import java.util.List;

public interface CommunicationLineInfoService {
    /**
     * 新增
     * @param communicationLineInfo
     * @return
     */
    int insert(CommunicationLineInfo communicationLineInfo);

    /**
     * 删除
     * @param communicationLineInfo
     * @return
     */
    int delete(CommunicationLineInfo communicationLineInfo);

    /**
     * 更新
     * @param communicationLineInfo
     * @return
     */
    int update(CommunicationLineInfo communicationLineInfo);

    /**
     * 获取所有
     * @param communicationLineInfo
     * @return
     */
    List<CommunicationLineInfo> selectList(CommunicationLineInfo communicationLineInfo);

    /**
     * 获取单个
     * @param communicationLineInfo
     * @return
     */
    CommunicationLineInfo selectOne(CommunicationLineInfo communicationLineInfo);

    int checkLineNameExist(CommunicationLineInfo communicationLineInfo);

    List<CommunicationLineInfo> getLineByGroupId(CommunicationLineInfo communicationLineInfo);
}
