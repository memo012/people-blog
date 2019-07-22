package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.Users;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang
 * @Description:
 * @Date: 2019/6/20 0020 11:25
 **/
public interface UserService {
    /**
     * 通过手机号查询
     * @param phone 手机号
     * @return
     */
    Users findByPhone(String phone);


    /**
     * 注册
     * @param users
     * @return
     */
    int insUsers(Users users);

    /**
     * 登录验证
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    int findByPhonePass(String phone, String password);

    /**
     *  通过用户查询
     * @param name
     * @return
     */
    Users findByName(String name);

}
