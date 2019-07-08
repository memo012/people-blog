package com.qiang.service.impl;

import com.qiang.commons.BlogJSONResult;
import com.qiang.commons.TimeUtil;
import com.qiang.mapper.BlogMapper;
import com.qiang.pojo.BlogMessage;
import com.qiang.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int publishBlog(BlogMessage blogMessage) {
        String id = UUID.randomUUID().toString();
        blogMessage.setId(id);
        blogMessage.setLike(0);
        TimeUtil timeUtil = new TimeUtil();
        blogMessage.setCreateTime(timeUtil.getFormatDateForThree());
        return blogMapper.publishBlog(blogMessage);
    }
}
