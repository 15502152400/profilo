package per.tom.clientview.controller;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.tom.clientview.vo.JsonResultVo;

@RestControllerAdvice
public class GlobalExpextionHandler {

    @ExceptionHandler(ShiroException.class)
    public JsonResultVo handleShiroException(ShiroException s){
        s.printStackTrace();
        JsonResultVo result = JsonResultVo.fail("");
        if (s instanceof UnknownAccountException){
            result.setMessage("该用户不存在");
        }else if (s instanceof LockedAccountException){
            result.setMessage("该用户被禁用");
        }else if (s instanceof IncorrectCredentialsException){
            result.setMessage("用户密码错误");
        }else if (s instanceof AuthorizationException){
            result.setMessage("没有此操作权限");
        }else {
            result.setMessage("服务器忙，请稍后再试");
        }
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResultVo handleProfiloException(RuntimeException r){
        return JsonResultVo.fail(r);
    }

}
