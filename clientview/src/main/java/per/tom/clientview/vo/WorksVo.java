package per.tom.clientview.vo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Timestamp;
/* 用于后台查询到的作品传给前端 */
@Data
public class WorksVo implements Serializable {

    private Integer worksId;        // 作品id
    private String worksName;       // 作品名字
    private String worksEnName;     // 作品英文名字
    private String worksContent;    // 作品内容
    private String coverImg;        // 封面图片地址
    private Timestamp createTime;   // 创建时间
    private Timestamp updateTime;   // 更新时间
    private String firstCateName;   // 一级分类名字
    private String cateName;        // 分类名字
    private String desiName;        // 设计形式

}
