package com.qiang.mapper;

import com.qiang.pojo.BlogMessage;
import org.springframework.stereotype.Repository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description:
 * @Date: 2019/7/6 0006 15:08
 **/
@Repository
public interface BlogMapper {

    int publishBlog(BlogMessage blogMessage);
}
