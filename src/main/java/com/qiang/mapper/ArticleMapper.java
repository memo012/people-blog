package com.qiang.mapper;

import com.qiang.pojo.VO.BlogMessageVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description: 首页数据操作
 * @Date: 2019/7/8 0008 15:33
 **/
@Repository
public interface ArticleMapper {

    @Select("select * from blog order by id desc")
    List<BlogMessageVO> findBlog();

}
