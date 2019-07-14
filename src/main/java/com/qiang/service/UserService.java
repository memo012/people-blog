package com.qiang.service;

import com.qiang.pojo.Users;

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
    int findByPhone(String phone);


    /**
     * 注册
     * @param users
     * @return
     */
    int insUsers(Users users);

}
