package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import com.qiang.modules.sys.mapper.AdminMapper;
import com.qiang.modules.sys.mapper.BlogMapper;
import com.qiang.modules.sys.pojo.Label;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import com.qiang.modules.sys.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service.impl
 * @Description: 异步任务(数据库和redis保持一致)
 * @Date: 2019/8/9 0009 15:24
 **/
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updBlogLook(Long id) {
        if(redisOperator.hasKey(Constant.BLOG_DETAIL+id)){
            blogMapper.updLooksByAsyncId(id, (int)redisOperator.get(Constant.BLOG_DETAIL+id));
        }else{
            Long lookNum = blogMapper.findLooksById(id);
            redisOperator.set(Constant.BLOG_DETAIL+id, lookNum);
        }
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insUserPhone() {
        List<Users> users = adminMapper.findAllUsers();
        for (Users u:
             users) {
            // 手机号加入缓存
            redisOperator.hset(Constant.USER_PHONE_EXIST, u.getPhone(), 1);
        }
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insUserName() {
        List<Users> users = adminMapper.findAllUsers();
        for (Users u:
                users) {
            // 用户名加入缓存
            redisOperator.hset(Constant.USER_NAME_EXIST, u.getUsername(), 1);
        }
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insPageBlog(List<BlogMessageVO> list) {
        for (BlogMessageVO b:
             list) {
            // 存入缓存中(首页分页查询)
            redisOperator.lpush(Constant.PAGE_BLOG, b);
        }
    }

    @Async
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insLabelName(List<Label> list) {
        for (Label label:
             list) {
            redisOperator.lpush(Constant.LABEL_ALL, label);
        }
    }
}
