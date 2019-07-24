package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.GuestLikes;
import com.qiang.modules.sys.pojo.RepGuest;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service
 * @Description: 留言接口
 * @Date: 2019/7/24 0024 17:30
 **/
public interface GuestService {


    /**
     * 查询全部留言
     * @return
     */
    List<Guest> getAllGuest();

    /**
     * 新增留言
     * @param guest
     * @return
     */
    List<Guest> insGuest(Guest guest);

    /**
     * 新增留言评论
     * @param repGuest
     * @return
     */
    List<Guest> insRepGuest(RepGuest repGuest);

    /**
     * 用户是否点赞
     * @param guestLikes
     * @return
     */
    boolean findIsLikes(GuestLikes guestLikes);

    /**
     * 删除用户点赞
     * @param guestLikes
     * @return
     */
    int delIsLikes(GuestLikes guestLikes);

    /**
     * 新增用户点赞信息
     * @param guestLikes
     * @return
     */
    List<Guest> insIsLikes(GuestLikes guestLikes);

    /**
     * 新增点赞
     * @param id
     * @return
     */
    int updInsLikes(Long id);

    /**
     * 减少点赞
     * @param id
     * @return
     */
    List<Guest> updDesLikes(Long id);

}
