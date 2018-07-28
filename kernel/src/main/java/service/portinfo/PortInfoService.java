package service.portinfo;

import model.portinfo.PortInfo;
import java.util.List;

public interface PortInfoService {
    /**
     * 新增
     * @param portInfo
     * @return
     */
    int insert(PortInfo portInfo);

    /**
     * 删除
     * @param portInfo
     * @return
     */
    int delete(PortInfo portInfo);

    /**
     * 更新
     * @param portInfo
     * @return
     */
    int update(PortInfo portInfo);

    /**
     * 获取所有
     * @param portInfo
     * @return
     */
    List<PortInfo> selectList(PortInfo portInfo);

    /**
     * 获取单个
     * @param portInfo
     * @return
     */
    PortInfo selectOne(PortInfo portInfo);

    int checkPortIpExist(PortInfo portInfo);

    List<PortInfo> getPortByGroupId(PortInfo portInfo);
}
