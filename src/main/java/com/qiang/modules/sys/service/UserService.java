package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.*;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import org.apache.catalina.User;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang
 * @Description:
 * @Date: 2019/6/20 0020 11:25
 **/
public interface UserService {
    /**
     * 通过手机号查询
     * @param phone 手机号
     * @return
     */
    Users findByPhone(String phone);


    /**
     * 注册
     * @param users
     * @return
     */
    int insUsers(Users users);

    /**
     * 登录验证
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    int findByPhonePass(String phone, String password);


    /**
     * 修改密码
     * @param phone
     * @param password
     * @return
     */
    int updUserPwd(String phone, String password);

    /**
     *  通过用户查询
     * @param name
     * @return
     */
    Users findByName(String name);

    /**
     * 个人信息
     * @param username
     * @return
     */
    Users findUserMess(String username);

    /**
     * 新增个人信息
     * @param users
     * @return
     */
    Users insUserMess(Users users);

    /**
     * 我的点赞
     * @param username
     * @return
     */
    List<CommentLikes> findLikes(String username);

    /**
     * 全部消息已读
     * @param username
     * @return
     */
    List<CommentLikes> updComIsRead(String username);

    /**
     * 我的留言（管理员）
     * @return
     */
    List<Guest> findAllGuest();

    /**
     * 我的留言（用户）
     * @param username
     * @return
     */
    List<RepGuest> findNotRepReadGuest(String username);

    /**
     * 全部消息已读（留言 --- 用户）
     * @param username
     * @return
     */
    List<RepGuest> updNotGuest(String username);

    /**
     * 部分留言消息已读（管理员）
     * @param id
     * @return
     */
    int updOneNotGuestMana(Long id);

    /**
     * 部分留言消息已读（用户）
     * @param id
     * @return
     */
    int updOneNotGuestUser(Long id);

    /**
     * 我的博客评论
     * @return
     */
    List<Comment> findAllComment();

    /**
     * 我的博客评论部分消息已读
     * @param id
     * @return
     */
    int updOneBlogNotComm(Long id);


    /**
     * 我的点赞评论部分消息已读
     * @param id
     * @return
     */
    int updOneBlogNotLikes(Long id);

    /**
     *  反馈（管理员）
     * @param username
     * @return
     */
    int findMessageNotRead(String username);

    /**
     *  消息（未读）
     * @param username
     * @return
     */
    int findMessageNotReadUser(String username);




}
