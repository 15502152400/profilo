package per.tom.clientview.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PagenationVo implements Serializable {

    private Integer currentPage;
    private Integer pageSize;
    private Integer countPage;
    private Integer countRow;

    public PagenationVo(Integer currentPage,Integer pageSize,Integer countRow){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.countRow = countRow;
        if (pageSize>0){
            this.countPage = countRow%pageSize==0?countRow/pageSize:countRow/pageSize+1;
        }else {
            this.countPage = 1;
        }
    }

}
