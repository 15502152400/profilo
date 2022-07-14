package per.tom.clientview.pojo;

import lombok.Data;
import per.tom.clientview.vo.WorksSaveVo;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class WorksPo implements Serializable {

    public WorksPo(){}

    public WorksPo(WorksSaveVo wsv,String coverPath){
        this.worksId = wsv.getWorksId();
        this.worksName = wsv.getWorksName();
        this.worksEnName = wsv.getWorksEnName();
        this.worksContent = wsv.getWorksContent();
        this.coverPath = coverPath;
        this.cateId = wsv.getWorksCateId();
        this.desiId = wsv.getWorksDesiId();
    }

    private Integer worksId;        // 作品id
    private String worksName;       // 作品名字
    private String worksEnName;     // 作品英文名字
    private String worksContent;    // 作品内容
    private String coverPath;       // 封面地址
    private Timestamp createTime;   // 创建时间
    private Timestamp updateTime;   // 更新时间
    private Integer parentCateId;   // 一级分类id
    private Integer cateId;         // 分类id
    private Integer desiId;         // 设计形式id

}
