package per.tom.clientview.service;

import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.WorksSaveVo;
import per.tom.clientview.vo.WorksVo;

public interface WorksService {
    JsonResultVo getWorksByCateId(Integer cateId, Integer currentPage, Integer pageSize);

    WorksVo getWorkByWorkId(Integer worksId);

    void saveProfilo(WorksSaveVo worksVo);
}
