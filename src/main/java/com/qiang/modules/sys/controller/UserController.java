package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 用户管理
 * @Date: 2019/7/14 0014 16:05
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param users
     * @return
     */
    @PostMapping("register")
    public BlogJSONResult register(@RequestBody Users users){
        int result = userService.insUsers(users);
        if(result > 0){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("注册失败");
    }


    /**
     * 手机号检测
     * @param phone
     * @return
     */
    @GetMapping("phoneCheck")
    public BlogJSONResult phoneCheck(@RequestParam("phone") String phone){
        Users result = userService.findByPhone(phone);
        if(result == null){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("该手机号已被注册");
    }

    /**
     * 用户检测
     * @param username
     * @return
     */
    @GetMapping("usernameCheck")
    public BlogJSONResult usernameCheck(@RequestParam("username") String username){
        Users result = userService.findByName(username);
        if(result == null){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("该用户已存在");
    }

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
            session.setAttribute("user", user);
            return BlogJSONResult.ok(user);
        }catch (AuthenticationException e){
            return BlogJSONResult.errorMsg("输入的用户名或密码错误");
        }
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("logout")
    public BlogJSONResult logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return BlogJSONResult.ok();
    }

    /**
     * 该用户是否过期
     * @param request
     * @return
     */
    @GetMapping("/isLogin")
    public BlogJSONResult isLogin(HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorMsg("用户已过期");
        }
        return BlogJSONResult.ok();
    }

    /**
     * 获取个人信息
     * @param username
     * @return
     */
    @GetMapping("getUserMess")
    public BlogJSONResult getUserMess(@RequestParam("username") String username){
        Users userMess = userService.findUserMess(username);
        return BlogJSONResult.ok(userMess);
    }

    /**
     * 新增个人信息
     * @param users
     * @return
     */
    @PostMapping("/insUserMess")
    public BlogJSONResult insUserMess(@RequestBody Users users){
        Users users1 = userService.insUserMess(users);
        if(users1 != null){
            return BlogJSONResult.ok(users1);
        }else{
            return BlogJSONResult.errorMsg("修改失败");
        }
    }


}
