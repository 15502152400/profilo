package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.pojo.CatePo;

import java.util.List;

@Mapper
public interface CateDao {
    List<CatePo> selectCateByParentCateId(Integer parentCateId);

    Integer[] selectCateIdByParentCateId(Integer cateId);

    Integer getParentCateIdByCateId(Integer cateId);

    void insertCate(CatePo catePo);

    List<CatePo> selectAllCateByParentCateId(Integer startIndex, Integer pageSize, Integer showOrder, Integer parenCateId);


    Integer countCateByParentCateId(Integer parenCateId);

    Integer deleteCatesByCateIdList(Integer[] cateIdList);

    Integer deleteCateByCateId(Integer cateId);

    void updateCateByCateId(CatePo catePo);
}
