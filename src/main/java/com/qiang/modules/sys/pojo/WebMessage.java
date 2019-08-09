package com.qiang.modules.sys.pojo;

import lombok.Data;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 网站信息
 * @Date: 2019/8/9 0009 18:01
 **/
@Data
public class WebMessage {

    /**
     * 主键
     */
    private String id;

    /**
     * 访问量
     */
    private Long visitor;

}
