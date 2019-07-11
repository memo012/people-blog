package com.qiang.pojo;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.pojo
 * @Description: 权限表
 * @Date: 2019/7/11 0011 20:16
 **/
public class Permission {
    private Integer pid;
    private String name;
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
