package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.pojo.DesiPo;

import java.util.List;

@Mapper
public interface DesiDao {
    List<DesiPo> getAllDesi();
}
