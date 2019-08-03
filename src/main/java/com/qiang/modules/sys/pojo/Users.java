package com.qiang.modules.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.pojo
 * @Description: 用户信息表
 * @Date: 2019/7/4 0004 11:13
 **/
@Data
public class Users implements Serializable {


    private static final long serialVersionUID = 2091272315817336009L;
    /**
     * 标识符id
     */
    private String id;

    /**
     * 用户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别（0-男 1-女）
     */
    private int sex;

    /**
     * 最后一次登录时间
     */
    private String lastTime;

    /**
     * 角色(1-超级管理员 2-普通用户 3-会员)
     */
    private int roleId;

    /**
     * 真实姓名
     */
    private String realname = "";

    /**
     * QQ号
     */
    private String qq = "";

    /**
     * 邮箱
     */
    private String email = "";

    /**
     * 个人简历
     */
    private String intro = "";

    /**
     *  角色
     */
    private Set<Role> roles;


}
