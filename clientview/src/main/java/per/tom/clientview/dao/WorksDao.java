package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.vo.WorksVo;

import java.util.List;

@Mapper
public interface WorksDao {

    List<WorksVo> selectWorksByCateId(Integer cateId, Integer startIndex, Integer pageSize);

    List<WorksVo> selectWorksByCateIdArr(Integer[] cateIdArr, Integer startIndex, Integer pageSize);

    Integer countWorksByCateIdArr(Integer[] cateIdArr);

    Integer countWorksByCateId(Integer cateId);

    WorksVo getWorkByWorkId(Integer worksId);
}
