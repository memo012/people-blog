package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.es.EsBlogMessage;
import org.springframework.data.domain.Page;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service
 * @Description: Es业务接口
 * @Date: 2019/7/13 0013 19:36
 **/
public interface EsService {

    /**
     * 保存更新blog
     * @param esBlogMessage
     * @return
     */
    EsBlogMessage saveBlog(EsBlogMessage esBlogMessage);

    /**
     * id查询
     * @param id
     * @return
     */
    EsBlogMessage findById(long id);

    /**
     * 分页搜索文章
     * @param page 显示数据(一页)
     * @param pageSize 当前页数
     * @param key 关键字
     * @return
     */
    Page<EsBlogMessage> findAllBlog(Integer page, Integer pageSize, String key);

}
