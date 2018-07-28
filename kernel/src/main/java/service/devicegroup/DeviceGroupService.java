package service.devicegroup;

import model.devicegroup.DeviceGroup;
import java.util.List;

public interface DeviceGroupService {
    /**
     * 新增
     * @param deviceGroup
     * @return
     */
    int insert(DeviceGroup deviceGroup);

    /**
     * 删除
     * @param deviceGroup
     * @return
     */
    int delete(DeviceGroup deviceGroup);

    /**
     * 更新
     * @param deviceGroup
     * @return
     */
    int update(DeviceGroup deviceGroup);

    /**
     * 获取所有
     * @param deviceGroup
     * @return
     */
    List<DeviceGroup> selectList(DeviceGroup deviceGroup);

    /**
     * 获取单个
     * @param deviceGroup
     * @return
     */
    DeviceGroup selectOne(DeviceGroup deviceGroup);
}
