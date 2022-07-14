package per.tom.clientview.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.pojo.UserPo;
import per.tom.clientview.service.UserService;
import per.tom.clientview.vo.JsonResultVo;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public JsonResultVo addUser(UserPo userPo){
        String message = userService.addUser(userPo);
        return JsonResultVo.success(message);
    }

    @PostMapping("doLogIn")
    public JsonResultVo doLogIn(String userTel,String userPass,boolean isRememberMe){
        /* 获取subject对象 */
        Subject subject = SecurityUtils.getSubject();
        /* 把用户信息封装到token对象 */
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userTel,userPass);
        /* 设置免登陆 */
        if(isRememberMe) {usernamePasswordToken.setRememberMe(true);}
        /* 对用户信息进行身份认证，此时会将token提交给SecurityManager */
        subject.login(usernamePasswordToken);

        return JsonResultVo.success();
    }

}
