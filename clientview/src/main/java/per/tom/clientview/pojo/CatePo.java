package per.tom.clientview.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class CatePo implements Serializable {

    private Integer cateId;         // 分类id
    private String cateName;        // 分类名字
    private Integer parentCateId;   // 上级分类ID，没有则为0
    private Timestamp createTime;   // 创建时间
    private Timestamp updateTime;   // 更新时间

}
