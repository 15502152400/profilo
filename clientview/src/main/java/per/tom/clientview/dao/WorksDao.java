package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.pojo.WorksPo;
import per.tom.clientview.vo.WorksVo;

import java.util.List;

@Mapper
public interface WorksDao {

    List<WorksVo> selectWorksByCateId(Integer cateId, Integer startIndex, Integer pageSize);

    List<WorksVo> selectWorksByCateIdArr(Integer[] cateIdArr, Integer startIndex, Integer pageSize);

    Integer countWorksByCateIdArr(Integer[] cateIdArr);

    Integer countWorksByCateId(Integer cateId);

    WorksVo getWorkByWorkId(Integer worksId);

    void insertWorks(WorksPo worksPo);

    List<WorksVo> getWorksByCateAddrDesi(Integer[] cateIdAddr, Integer desiId, Integer timeOrder, Integer startIndex, Integer pageSize);

    Integer countWorksByCateAddrDesi(Integer[] cateIdAddr, Integer desiId);

    List<WorksVo> getWorksByCateIdDesi(Integer cateId, Integer desiId, Integer timeOrder, Integer startIndex, Integer pageSize);

    Integer countWorksByCateIdDesi(Integer cateId, Integer desiId);

    Integer deleteWorksByWorkIds(Integer[] workIds);

    Integer deleteWorkByWorkId(Integer workId);

    WorksPo getWorkAllByWorkId(Integer worksId);

    String getWorkCoverByWorkId(Integer workId);

    void updateWorkByWorkId(WorksPo worksPo);

    WorksPo checkIsHaveProfiloByCateId(Integer cateId);
}
