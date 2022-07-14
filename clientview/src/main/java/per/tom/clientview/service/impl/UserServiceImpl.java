package per.tom.clientview.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.tom.clientview.config.UserConfig;
import per.tom.clientview.dao.UserDao;
import per.tom.clientview.pojo.UserPo;
import per.tom.clientview.service.UserService;
import per.tom.clientview.utils.AssertBase;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserConfig userConfig;

    @Autowired
    UserDao userDao;

    @Autowired
    AssertBase assertBase;

    @Override
    public String addUser(UserPo userPo) {

        // 校验参数
        checkAddUser(userPo);

        // 设置盐值
        userPo.setUserSalt(UUID.randomUUID().toString());

        // 加密密码
        userPo.setUserPassword(new SimpleHash("MD5",userPo.getUserPassword(),userPo.getUserSalt(),1).toHex());

        // 执行添加
        Integer rows = userDao.insertUser(userPo);

        return "注册成功";
    }

    @Override
    public UserPo findUserByTel(String userTel) {
        assertBase.assertBase(userTel.length()!=11);
        UserPo userPo = userDao.selectUserByTel(userTel);
        assertBase.assertBase(userPo==null);
        return userPo;
    }


    // 添加用户的参数校验方法
    public void checkAddUser(UserPo u){
        String m = "您有必填项目未填写或者未填写正确";
        assertBase.asserNumber(u.getUserGender(), 1, 0,m);
        assertBase.asserString(u.getUserName(), userConfig.getMaxUserName(), userConfig.getMinUserName(),m);
        assertBase.assertTel(u.getUserTel());
        assertBase.assertPass(u.getUserPassword());
    }

}
