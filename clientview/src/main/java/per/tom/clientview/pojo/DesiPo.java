package per.tom.clientview.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class DesiPo implements Serializable {

    private Integer desiId;         // 设计形式id
    private String desiName;        // 设计形式名字
    private Timestamp createTime;   // 创建时间
    private Timestamp updateTime;   // 更新时间

}
