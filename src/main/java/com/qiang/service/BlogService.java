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
    void publishBlog(BlogMessage blogMessage);

    /**
     * 通过id查询该文章详情
     * @param id
     * @return
     */
    BlogMessage findById(long id);

}
