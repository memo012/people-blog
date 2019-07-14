package com.qiang.controller;

import com.qiang.commons.BlogJSONResult;
import com.qiang.pojo.Users;
import com.qiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        int result = userService.findByPhone(phone);
        if(result == 0){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("该手机号已被注册");
    }

}
