package service.superlogo;

import model.superlogo.SuperLogo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SuperLogoService {
    /**
     * 新增
     * @param superLogo
     * @return
     */
    int insert(SuperLogo superLogo);

    /**
     * 删除
     * @param superLogo
     * @return
     */
    int delete(SuperLogo superLogo);

    /**
     * 更新
     * @param superLogo
     * @return
     */
    int update(SuperLogo superLogo);

    /**
     * 获取所有
     * @param superLogo
     * @return
     */
    List<SuperLogo> selectList(SuperLogo superLogo);

    /**
     * 获取单个
     * @param superLogo
     * @return
     */
    SuperLogo selectOne(SuperLogo superLogo);

    void logoUpload(MultipartFile files);
}
