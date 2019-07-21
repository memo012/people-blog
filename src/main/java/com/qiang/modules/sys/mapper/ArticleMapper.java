package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from blog where labelValues like '%${tag}%' order by id desc")
    List<BlogMessageVO> findByTag(@Param("tag") String tag);

    @Select("select * from blog where selectCategories = #{arg0} order by id desc")
    List<BlogMessageVO> findByCategories(String categories);

    @Select("select * from blog where createTime = #{arg0} order by id desc")
    List<BlogMessage> findByTime(String time);

    @Update("update blog set likes = likes + 1 where id = #{articleId}")
    int updLike(@Param("articleId") long articleId);

    @Select("select likes from blog where id = #{articleId}")
    int findLike(@Param("articleId") long articleId);

}
