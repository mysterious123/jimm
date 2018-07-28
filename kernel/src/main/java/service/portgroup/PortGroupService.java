package service.portgroup;

import model.portgroup.PortGroup;
import java.util.List;

public interface PortGroupService {
    /**
     * 新增
     * @param portGroup
     * @return
     */
    int insert(PortGroup portGroup);

    /**
     * 删除
     * @param portGroup
     * @return
     */
    int delete(PortGroup portGroup);

    /**
     * 更新
     * @param portGroup
     * @return
     */
    int update(PortGroup portGroup);

    /**
     * 获取所有
     * @param portGroup
     * @return
     */
    List<PortGroup> selectList(PortGroup portGroup);

    /**
     * 获取单个
     * @param portGroup
     * @return
     */
    PortGroup selectOne(PortGroup portGroup);
}
