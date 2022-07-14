package per.tom.clientview.vo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Timestamp;

/* 用于添加或者修改作品时接收前台传参 */
@Data
public class WorksSaveVo implements Serializable {

    private Integer worksId;        // 作品id
    private String worksName;       // 作品名字
    private String worksEnName;     // 作品英文名字
    private String worksContent;    // 作品内容
    private MultipartFile coverImg; // 封面图片
    private Integer worksCateId;    // 分类id
    private Integer worksDesiId;    // 设计形式id

}
