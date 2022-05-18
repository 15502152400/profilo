package per.tom.clientview.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class WorksPo implements Serializable {

    private Integer worksId;        // 作品id
    private String worksName;       // 作品名字
    private String worksEnName;     // 作品英文名字
    private String worksContent;    // 作品内容
    private String coverPath;       // 封面地址
    private Timestamp createTime;   // 创建时间
    private Timestamp updateTime;   // 更新时间
    private Integer cateId;         // 分类id
    private Integer desiId;         // 设计形式id

}
