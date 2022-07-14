package per.tom.clientview.service.impl;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.tom.clientview.dao.UserDao;
import per.tom.clientview.pojo.UserPo;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;

    /**设置凭证匹配器**/
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        /* 1. 创建凭证匹配器对象 */
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /* 2. 设置加密算法 */
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        /* 3. 设置加密次数 */
        hashedCredentialsMatcher.setHashIterations(1);
        /* 注意此位置的值 */
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 基于此方法获取用户信息并且封装，
     * 然后将封装的结果传递给SecurityManager对象，
     * 由此manager对象调用authenticate方法完成认证操作
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /* 1. 基于token获取客户端输入的用户名和密码 */
        UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
        String username = uptoken.getUsername();
        /* 2. 根据用户手机查询用户 */
        UserPo user = userDao.selectUserByTel(username);
        /* 3. 校验用户是否存在 */
        if(user==null)throw new UnknownAccountException("该用户不存在");
        /* 4. 校验用户是否禁用 */
        if(user.getUserStatus()==0) throw new LockedAccountException("该用户已经被禁用");
        /* 5. 封装信息,记住：构建什么对象要看方法的返回值 */
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserSalt());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, //用户身份，结合业务自己赋予具体对象
                user.getUserPassword(),//已经加密的密码
                credentialsSalt, //对登录面进行加密时使用的盐
                getName());//realmName
        /* 返回封装结果
         * 返回值会传递给认证管理器(后续
         * 认证管理器会通过此信息完成认证操作)*/
        return simpleAuthenticationInfo;
    }

    /**
     * 基于此方法进行授权信息的获取和封装
     * 会将封装好的数据传递给securityManager对象，由此对象进行用户权限检测和授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
