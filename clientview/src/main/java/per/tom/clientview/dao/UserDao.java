package per.tom.clientview.dao;

import org.apache.ibatis.annotations.Mapper;
import per.tom.clientview.pojo.UserPo;

@Mapper
public interface UserDao {
    Integer insertUser(UserPo userPo);

    UserPo selectUserByTel(String userTel);

}
