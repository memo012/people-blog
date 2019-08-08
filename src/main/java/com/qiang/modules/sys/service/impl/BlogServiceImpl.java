package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.StringAndArray;
import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.BlogMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
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

    @Autowired
    private RedisOperator redisOperator;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public BlogMessage findById(long id) {
        BlogMessage byId = null;
        if(redisOperator.hasHkey(Constant.BLOG_DETAIL, String.valueOf(id))){
            byId = (BlogMessage)redisOperator.hget(Constant.BLOG_DETAIL, String.valueOf(id));
            long incr = redisOperator.incr(Constant.BLOG_DETAIL + id, 1L);
            byId.setLook((int) incr);
            if(redisOperator.hasKey(Constant.BLOG_LIKES+id)){
                byId.setLike((int)redisOperator.get(Constant.BLOG_LIKES+id));
            }else{
                byId.setLike(0);
            }

        }else{
            blogMapper.updLooksById(id);
            byId = blogMapper.findById(id);
        }
        return byId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void publishBlog(BlogMessageVO blogMessageVO) {
        long id = 0L;
        BlogMessageVO blog = null;
        EsBlogMessage esBlogMessage = null;
        if (blogMessageVO.getId() == 0) {
            id = new TimeUtil().getLongTime();
            blogMessageVO.setId(id);
            blogMessageVO.setLike(0);
            blogMessageVO.setLook(0);
            TimeUtil timeUtil = new TimeUtil();
            blogMessageVO.setCreateTime(timeUtil.getFormatDateForThree());
            blogMessageVO.setTagValue(StringAndArray.stringToArray(blogMessageVO.getLabelValues()));
            blogMessageVO.setArticleUrl("/article/" + blogMessageVO.getId());
            blogMapper.publishBlog(blogMessageVO);
            blog = blogMapper.findById(id);
            esBlogMessage = new EsBlogMessage(blog);
        }else{
            esBlogMessage = esService.findById(blogMessageVO.getId());
            esBlogMessage.update(blogMessageVO);
        }
        esService.saveBlog(esBlogMessage);
        // 存入缓存中(首页分页查询)
        redisOperator.lpush(Constant.PAGE_BLOG, blogMessageVO);
        // 存入缓存（博客具体详情）
        redisOperator.hset(Constant.BLOG_DETAIL, String.valueOf(blogMessageVO.getId()), blog);
        // 文章浏览次数
        redisOperator.set(Constant.BLOG_DETAIL+blogMessageVO.getId(), 0);
    }
}
