package per.tom.clientview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import per.tom.clientview.pojo.UserPo;
import per.tom.clientview.pojo.WorksPo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.service.UserService;
import per.tom.clientview.service.WorksService;
import per.tom.clientview.vo.JsonResultVo;

@SpringBootTest
class ClientviewApplicationTests {

//    @Autowired
//    UserService service;
//
//    @Autowired
//    WorksService worksService;
//
//    @Autowired
//    CateService cateService;
//
//    @Test
//    void contextLoads() {
//
//        UserPo userPo = new UserPo();
//        userPo.setUserName("15502152400");
//        userPo.setUserPassword("a349499846");
//        userPo.setUserTel("15502152400");
//        userPo.setUserGender(1);
//        userPo.setRoleId(1);
//        service.addUser(userPo);
//
//    }
//
//    @Test
//    void getWorks(){
//        WorksPo workAllByWorkId = worksService.getWorkAllByWorkId(2);
//        System.out.println(workAllByWorkId);
//    }
//
//    @Test
//    void tets1(){
//        JsonResultVo allCate = cateService.getAllCate(1, 10, 0, -1);
//        System.out.println(allCate);
//    }
//
//    @Test
//    void test2(){
//        Integer[] a = {16,15};
//        System.out.println(cateService.deleteCatesByCateIdList(a));
//    }

}
