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

    @Autowired
    private AsyncServiceImpl asyncService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public BlogMessage findById(long id) {
        BlogMessage blogMessage = null;
        // 从缓存中查询
        if(redisOperator.hasHkey(Constant.BLOG_DETAIL, String.valueOf(id))){
            blogMessage = (BlogMessage)redisOperator.hget(Constant.BLOG_DETAIL, String.valueOf(id));
            long looks = 0L; // 浏览次数
            int likes = 0; // 点赞数

            // 缓存中是否存在博客浏览数
            if(redisOperator.hasKey(Constant.BLOG_DETAIL + id)){
                looks = redisOperator.incr(Constant.BLOG_DETAIL + id, 1L);
                // 异步存储
                asyncService.updBlogLook(id);
            }else{
                Long lookNum = blogMapper.findLooksById(id);
                looks = lookNum + 1;
                redisOperator.set(Constant.BLOG_DETAIL + id, looks);
            }

            // 缓存中是否存在博客点赞数
            if(redisOperator.hasKey(Constant.BLOG_LIKES+id)){
                likes = (int)redisOperator.get(Constant.BLOG_LIKES + id);
            }else{
                int like = blogMapper.findLikesById(id);
                redisOperator.set(Constant.BLOG_LIKES + id, like);
            }
            blogMessage.setLike(likes);
            blogMessage.setLook((int)looks);

        }else{
            // 从数据库中查 ， 然后存入缓存中
            blogMessage = blogMapper.findById(id);
            redisOperator.hset(Constant.BLOG_DETAIL, String.valueOf(id), blogMessage);
        }
        return blogMessage;
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
