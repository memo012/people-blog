package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.BlogMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.es.EsBlogMessage;
import com.qiang.modules.sys.service.BlogService;
import com.qiang.modules.sys.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service.impl
 * @Description: 博客业务逻辑层
 * @Date: 2019/7/6 0006 15:07
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private EsService esService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public BlogMessage findById(long id) {
        blogMapper.updLooksById(id);
        BlogMessage byId = blogMapper.findById(id);
        return byId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void publishBlog(BlogMessage blogMessage) {
        long id = 0L;
        BlogMessage blog = null;
        EsBlogMessage esBlogMessage = null;
        if (blogMessage.getId() == 0) {
            id = new TimeUtil().getLongTime();
            blogMessage.setId(id);
            blogMessage.setLike(0);
            blogMessage.setLook(0);
            TimeUtil timeUtil = new TimeUtil();
            blogMessage.setCreateTime(timeUtil.getFormatDateForThree());
            blogMapper.publishBlog(blogMessage);
            blog = blogMapper.findById(id);
            esBlogMessage = new EsBlogMessage(blog);
        }else{
            esBlogMessage = esService.findById(blogMessage.getId());
            esBlogMessage.update(blogMessage);
        }
        esService.saveBlog(esBlogMessage);
    }
}
