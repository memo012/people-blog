package com.qiang.shiro;

import com.qiang.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.shiro
 * @Description: shiro认证授权
 * @Date: 2019/6/20 0020 13:02
 **/
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 用于授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 用户认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       return null;
    }
}
