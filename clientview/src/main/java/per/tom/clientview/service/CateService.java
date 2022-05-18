package per.tom.clientview.service;

import per.tom.clientview.pojo.CatePo;

import java.util.List;

public interface CateService {
    List<CatePo> getCateByParentCateId(Integer parentCateId);

    Integer getParentCateIdByCateId(Integer cateId);
}
