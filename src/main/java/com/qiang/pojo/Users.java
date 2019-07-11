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
    private Data lastTime;

    /**
     * 角色(1-超级管理员 2-普通用户 3-会员)
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Data getLastTime() {
        return lastTime;
    }

    public void setLastTime(Data lastTime) {
        this.lastTime = lastTime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
