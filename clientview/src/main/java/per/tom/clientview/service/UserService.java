package per.tom.clientview.service;

import per.tom.clientview.pojo.UserPo;

public interface UserService {
    String addUser(UserPo userPo);

    UserPo findUserByTel(String userTel);
}
