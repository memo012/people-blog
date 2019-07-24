package com.qiang.modules.sys.pojo;

import lombok.Data;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 留言评论实体类
 * @Date: 2019/7/24 0024 17:16
 **/
@Data
public class RepGuest {

    /**
     * 主键
     */
    private Long rid;

    /**
     * 留言条id
     */
    private Long guestId;

    /**
     * 回复内容
     */
    private String repMess;

    /**
     * 评论者id
     */
    private String rguestId;

    /**
     * 创建时间
     */
    private String rcreateTime;

    /**
     * 1 - 未读  0 - 已读
     */
    private Integer risRead = 1;

    /**
     * 评论者名称
     */
    private String repName;

    /**
     *被评论者名称
     */
    private String guestName;

}
