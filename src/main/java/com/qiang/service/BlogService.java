package com.qiang.service;

import com.qiang.pojo.BlogMessage;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service
 * @Description: 博客发布接口
 * @Date: 2019/7/6 0006 15:05
 **/
public interface BlogService {

    /**
     * 博客发布
     * @param blogMessage 博客实体类
     * @return
     */
    int publishBlog(BlogMessage blogMessage);

}
