package service.deviceinfo;

import model.deviceinfo.DeviceInfo;
import java.util.List;

public interface DeviceInfoService {
    /**
     * 新增
     * @param deviceInfo
     * @return
     */
    int insert(DeviceInfo deviceInfo);

    /**
     * 删除
     * @param deviceInfo
     * @return
     */
    int delete(DeviceInfo deviceInfo);

    /**
     * 更新
     * @param deviceInfo
     * @return
     */
    int update(DeviceInfo deviceInfo);

    /**
     * 获取所有
     * @param deviceInfo
     * @return
     */
    List<DeviceInfo> selectList(DeviceInfo deviceInfo);

    /**
     * 获取单个
     * @param deviceInfo
     * @return
     */
    DeviceInfo selectOne(DeviceInfo deviceInfo);
    /**
     * snmppoll
     * @param deviceInfo
     * @return
     */
    DeviceInfo snmpPoll(DeviceInfo deviceInfo);

    int checkIpExist(DeviceInfo deviceInfo);

    List<DeviceInfo> getDeviceByGroupId(DeviceInfo deviceInfo);


}
