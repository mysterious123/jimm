package service.venderinfo;

import model.venderinfo.VenderInfo;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface VenderInfoService {
    /**
     * 新增
     * @param venderInfo
     * @return
     */
    int insert(VenderInfo venderInfo);

    /**
     * 删除
     * @param venderInfo
     * @return
     */
    int delete(VenderInfo venderInfo);

    /**
     * 更新
     * @param venderInfo
     * @return
     */
    int update(VenderInfo venderInfo);

    /**
     * 获取所有
     * @param venderInfo
     * @return
     */
    List<VenderInfo> selectList(VenderInfo venderInfo);

    /**
     * 获取单个
     * @param venderInfo
     * @return
     */
    VenderInfo selectOne(VenderInfo venderInfo);

    /**
     * 上传logo /resources/images/logo
     * @param files
     * @return
     */
    void logoUpload(MultipartFile files);
}
