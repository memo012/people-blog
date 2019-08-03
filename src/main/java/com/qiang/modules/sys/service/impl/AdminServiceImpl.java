package com.qiang.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.common.utils.Constant;
import com.qiang.common.utils.PagedResult;
import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.StringAndArray;
import com.qiang.modules.sys.mapper.AdminMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import com.qiang.modules.sys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service.impl
 * @Description: 管理员业务逻辑层
 * @Date: 2019/8/2 0002 18:00
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisOperator redisOperator;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteBlog(Long id) {
        redisOperator.del(Constant.BLOG_DETAIL+id);
        redisOperator.hdel(Constant.BLOG_DETAIL, String.valueOf(id));
        BlogMessageVO byId = adminMapper.findById(id);
        System.out.println(byId.getLabelValues());
        byId.setTagValue(StringAndArray.stringToArray(byId.getLabelValues()));
        byId.setArticleUrl("/article/" + byId.getId());
        redisOperator.lremove(Constant.PAGE_BLOG, 0, byId);
        int i = adminMapper.delBlog(id);
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteUsers(String username) {
        int i = adminMapper.delUsers(username);
        return i;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findAllBlogs(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogMessage> allBlogs = adminMapper.findAllBlogs();
        PageInfo<BlogMessage> pageList = new PageInfo<>(allBlogs);
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(allBlogs);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findAllUsers(Integer pageSize,Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> allUsers = adminMapper.findAllUsers();
        PageInfo<Users> pageList = new PageInfo<>(allUsers);
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        grid.setRows(allUsers);
        return grid;
    }
}
