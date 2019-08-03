package com.qiang.modules.sys.pojo;

import java.io.Serializable;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 标签实体库
 * @Date: 2019/7/6 0006 11:45
 **/
public class Label implements Serializable {

    private static final long serialVersionUID = -8912589778594195822L;
    private String id;
    private String labelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
