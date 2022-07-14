package per.tom.clientview.service;

import org.springframework.stereotype.Service;
import per.tom.clientview.pojo.WorksPo;
import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.WorksSaveVo;
import per.tom.clientview.vo.WorksVo;

@Service
public interface WorksService {
    JsonResultVo getWorksByCateId(Integer cateId, Integer currentPage, Integer pageSize);

    WorksVo getWorkByWorkId(Integer worksId);

    void saveProfilo(WorksSaveVo worksVo);

    JsonResultVo getWorksByCateDesi(Integer cateId, Integer desiId, Integer timeOrder, Integer currentPage, Integer pageSize);

    void deleteWorksByWorkIds(Integer[] workIds);

    void deleteWorkByWorkId(Integer workId);

    WorksPo getWorkAllByWorkId(Integer worksId);
}
