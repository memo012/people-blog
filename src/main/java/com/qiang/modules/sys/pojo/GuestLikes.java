package com.qiang.modules.sys.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 留言点赞用户判断
 * @Date: 2019/7/24 0024 19:51
 **/
@Data
public class GuestLikes implements Serializable {

    private static final long serialVersionUID = -4126217973825926180L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 留言者id
     */
    private Long guestId;

    /**
     * 点赞名称
     */
    private String likeName;

}
