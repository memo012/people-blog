package com.qiang.pojo;

import javax.xml.crypto.Data;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.pojo
 * @Description: 用户信息表
 * @Date: 2019/7/4 0004 11:13
 **/
public class Users {

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
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private int sex;

    /**
     * 最后一次登录时间
     */
    private Data datetime;

    /**
     * 角色(1-超级管理员 2-普通用户)
     */
    private int roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Data getDatetime() {
        return datetime;
    }

    public void setDatetime(Data datetime) {
        this.datetime = datetime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
