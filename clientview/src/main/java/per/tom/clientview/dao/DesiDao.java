package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.pojo.DesiPo;

import java.util.List;

@Mapper
public interface DesiDao {
    List<DesiPo> getAllDesi();

    void saveDesi(String desiName);

    List<DesiPo> getAllDesiByPagenation(Integer currentPage, Integer pageSize, Integer showOrder, Integer startIndex);

    Integer countDesi();

    void deleteDesiByDesiIdList(Integer[] desiIdList);

    void deleteDesiByDesiId(Integer desiId);

    void updateDesiByDesiId(Integer desiId, String desiName);
}
