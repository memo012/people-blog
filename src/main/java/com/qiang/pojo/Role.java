package com.qiang.pojo;

import java.util.Set;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.pojo
 * @Description: 角色表
 * @Date: 2019/7/11 0011 20:13
 **/
public class Role {

    /**
     * 标识符
     */
    private Integer rid;

    /**
     * 角色名
     */
    private String rname;

    /**
     * 当前角色所具备的权限
     */
    private Set<Permission> permissionSet;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
}
