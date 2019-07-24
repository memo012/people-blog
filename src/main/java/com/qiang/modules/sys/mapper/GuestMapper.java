package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.GuestLikes;
import com.qiang.modules.sys.pojo.RepGuest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.mapper
 * @Description: 留言数据操作
 * @Date: 2019/7/24 0024 17:19
 **/
@Repository
public interface GuestMapper {

    List<Guest> getAllGuest();

    int insGuest(Guest guest);

    int insRepGuest(RepGuest repGuest);

    @Update("update guest set likes = likes + 1 where id = #{arg0}")
    int updInsRepGuest(Long id);

    @Update("update guest set likes = likes - 1 where id = #{arg0}")
    int updDesRepGuest(Long id);

    @Select("select count(*) from guestlikes where guestId = #{guestId} and likeName = #{likeName}")
    int findIsLikes(GuestLikes guestLikes);

    int insIsLikes(GuestLikes guestLikes);

    @Delete("delete from guestlikes where guestId = #{guestId} and likeName = #{likeName}")
    int delIsLike(GuestLikes guestLikes);

}
