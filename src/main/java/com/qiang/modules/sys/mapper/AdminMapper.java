package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.mapper
 * @Description: 管理员mapper
 * @Date: 2019/8/2 0002 17:58
 **/
@Repository
public interface AdminMapper {

    @Select("select * from users")
    List<Users> findAllUsers();

    @Select("select * from blog order by id desc")
    List<BlogMessage> findAllBlogs();

    @Delete("delete from users where username = #{arg0}")
    int delUsers(String username);

    @Delete("delete from blog where id = #{arg0}")
    int delBlog(Long id);


    @Select("select * from blog where id = #{arg0}")
    BlogMessageVO findById(Long id);

    @Select("select * from blog where id = #{arg0}")
    BlogMessage editByBlogId(Long id);

}
