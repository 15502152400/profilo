package per.tom.clientview.service;

import per.tom.clientview.pojo.CatePo;
import per.tom.clientview.vo.JsonResultVo;

import java.util.List;

public interface CateService {
    List<CatePo> getCateByParentCateId(Integer parentCateId);

    Integer getParentCateIdByCateId(Integer cateId);

    Integer[] getCateIdByParentCateId(Integer cateId);

    void saveCate(CatePo catePo);

    JsonResultVo getAllCate(Integer currentPage, Integer pageSize, Integer showOrder, Integer parenCateId);

    JsonResultVo deleteCatesByCateIdList(Integer[] cateIdList);

    void deleteCateByCateId(Integer cateId);

    void updateCateByCateId(CatePo catePo);
}
