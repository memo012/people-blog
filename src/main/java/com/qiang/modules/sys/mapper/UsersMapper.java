package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description:
 * @Date: 2019/6/20 0020 21:30
 **/
@Repository
public interface UsersMapper {

    Users findByPhone(String phone);

    int insUsers(Users users);

    @Select("select count(*) from user where phone = #{arg0} and password = #{arg1}")
    int findByPhonePass(String phone, String password);

    @Select("select * from user where username = #{arg0}")
    Users findByName(String name);

    @Select("select * from user where username = #{arg0}")
    Users findUserMess(String username);


    int updUserMess(Users users);


    List<CommentLikes> findLikes(String username);

    @Select("select cl.id from comment c INNER JOIN commentlikes cl on c.id = cl.commentId where c.authorName = #{arg0} and cl.isRead = 1")
    List<Long> findNotReadLikesIdByUsername(String username);

    int updLikesIsRead(List<Long> rid);

    @Select("select * from guest")
    List<Guest> findAllGuest();

    @Select("select * from repguest where guestName = #{argo}")
    List<RepGuest> findNotRepReadGuest(String username);

    @Select("select rid from repguest where guestName = #{arg0} and risRead = 1")
    List<Long> findNotGuest(String username);

    int updGuestIsRead(List<Long> rid);

    @Update("update guest set isRead = 0 where id = #{arg0}")
    int updOneNotGuestMana(Long id);

    @Update("update repguest set risRead = 0 where id = #{arg0}")
    int updOneNotGuestUser(Long id);


    @Select("select * from comment")
    List<Comment> findAllComment();

    @Update("update comment set isRead = 0 where id = #{arg0}")
    int updOneBlogNotComm(Long id);

    @Update("update commentlikes set isRead = 0 where id = #{arg0}")
    int updOneBlogNotLikes(Long id);

    @Select("select count(*) from comment where isRead = 1")
    int findNotReadComm();

    @Select("select count(*) from guest where isRead = 1")
    int findNotReadGuest();

    @Select("select count(*) from reportcomment where risRead = 1 and comName = #{arg0}")
    int findNotReadRepComm(String username);

    @Select("select count(*) from repguest where risRead = 1 and guestName = #{arg0}")
    int findNotReadRepGuest(String username);




}
