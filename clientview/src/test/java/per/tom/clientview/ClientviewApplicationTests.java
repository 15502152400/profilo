package per.tom.clientview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.tom.clientview.pojo.UserPo;
import per.tom.clientview.service.UserService;

@SpringBootTest
class ClientviewApplicationTests {

    @Autowired
    UserService service;

    @Test
    void contextLoads() {

        UserPo userPo = new UserPo();
        userPo.setUserName("15502152400");
        userPo.setUserPassword("a349499846");
        userPo.setUserTel("15502152400");
        userPo.setUserGender(1);
        userPo.setRoleId(1);
        service.addUser(userPo);

    }

}
