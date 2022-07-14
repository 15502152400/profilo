package per.tom.clientview.service;

import org.springframework.stereotype.Service;
import per.tom.clientview.pojo.UserPo;
@Service
public interface UserService {
    String addUser(UserPo userPo);

    UserPo findUserByTel(String userTel);
}
