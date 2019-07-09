package com.qiang.service;

import com.qiang.commons.PagedResult;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service
 * @Description: 首页操作业务逻辑接口
 * @Date: 2019/7/8 0008 15:45
 **/
public interface ArticleService {

    /**
     * 分页查询文章
     * @param page 显示数据(一页)
     * @param pageSize 当前页数
     * @return
     */
    PagedResult findAllBlog(Integer page, Integer pageSize);

}
