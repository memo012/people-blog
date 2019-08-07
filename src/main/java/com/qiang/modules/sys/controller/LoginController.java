package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.modules.sys.pojo.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description:
 * @Date: 2019/8/4 0004 12:21
 **/
@RestController
public class LoginController {

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @param session
     * @return
     */
    @GetMapping("loginUser")
    public BlogJSONResult loginUser(@RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
//        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            Users user = (Users)subject.getPrincipal();
            user.setPassword("");
            System.out.println(user.getRoles());
            session.setAttribute("user", user);
            return BlogJSONResult.ok(user);
        }catch (AuthenticationException e){
            return BlogJSONResult.errorMsg("输入的用户名或密码错误");
        }
    }

}
