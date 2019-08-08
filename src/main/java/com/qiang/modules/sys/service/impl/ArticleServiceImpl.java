package com.qiang.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.common.utils.Constant;
import com.qiang.common.utils.PagedResult;
import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.StringAndArray;
import com.qiang.modules.sys.mapper.ArticleMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import com.qiang.modules.sys.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service.impl
 * @Description: 首页操作业务逻辑层
 * @Date: 2019/7/8 0008 15:47
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisOperator redisOperator;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updLike(long articleId) {
        // articleMapper.updLike(articleId);
        redisOperator.incr(Constant.BLOG_LIKES+articleId, 1);
        return (int)redisOperator.get(Constant.BLOG_LIKES+articleId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findByTime(Integer page, Integer pageSize, String time) {
        PageHelper.startPage(page, pageSize);
        List<BlogMessage> blog = articleMapper.findByTime(time);
        PageInfo<BlogMessage> pageList = new PageInfo<>(blog);
        for (BlogMessage b:
                blog) {
            b.setTagValue(StringAndArray.stringToArray(b.getLabelValues()));
        }
        PagedResult grid = new PagedResult();
        grid.setPage(page);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(blog);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findByCategories(Integer page, Integer pageSize, String categories) {
        PageHelper.startPage(page, pageSize);
        List<BlogMessageVO> blog = articleMapper.findByCategories(categories);
        for (BlogMessageVO b:
                blog) {
            b.setTagValue(StringAndArray.stringToArray(b.getLabelValues()));
        }
        PageInfo<BlogMessageVO> pageList = new PageInfo<>(blog);
        PagedResult grid = new PagedResult();
        grid.setPage(page);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(blog);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findByTag(Integer page, Integer pageSize, String tag) {
        PageHelper.startPage(page, pageSize);
        List<BlogMessageVO> blog = articleMapper.findByTag(tag);
        for (BlogMessageVO b:
                blog) {
            b.setTagValue(StringAndArray.stringToArray(b.getLabelValues()));
            b.setSpecificTag(tag);
        }
        PageInfo<BlogMessageVO> pageList = new PageInfo<>(blog);
        PagedResult grid = new PagedResult();
        grid.setPage(page);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(blog);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findAllBlog(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<BlogMessageVO> blog = articleMapper.findBlog();
        for (BlogMessageVO b:
             blog) {
            b.setTagValue(StringAndArray.stringToArray(b.getLabelValues()));
            b.setArticleUrl("/article/" + b.getId());
        }
        PageInfo<BlogMessageVO> pageList = new PageInfo<>(blog);
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(blog);
        return grid;
    }
}
