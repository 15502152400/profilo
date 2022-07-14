package per.tom.clientview.service;

import org.springframework.stereotype.Service;
import per.tom.clientview.pojo.DesiPo;
import per.tom.clientview.vo.JsonResultVo;

import java.util.List;

@Service
public interface DesiService {
    List<DesiPo> getAllDesi();

    void saveDesi(String desiName);

    JsonResultVo getAllDesiByPagenation(Integer currentPage, Integer pageSize, Integer showOrder);

    void deleteDesiByDesiIdList(Integer[] desiIdList);

    void deleteDesiByDesiId(Integer desiId);

    void updateDesiByDesiId(Integer desiId, String desiName);
}
