package com.qiang.mapper;

import com.qiang.pojo.BlogMessage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description: 博客数据发布操作
 * @Date: 2019/7/6 0006 15:08
 **/
@Repository
public interface BlogMapper {

    int publishBlog(BlogMessage blogMessage);

    @Select("select * from blog where id = #{arg0}")
    BlogMessage findById(Long id);

    @Update("update blog set look = look + 1 where id = #{arg0}")
    void updLooksById(Long id);
}
