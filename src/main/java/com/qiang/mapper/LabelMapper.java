package com.qiang.mapper;

import com.qiang.pojo.Label;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description: 标签数据库
 * @Date: 2019/7/5 0005 23:48
 **/
@Repository
public interface LabelMapper {

    @Insert("insert into label values(#{id}, #{labelName})")
    int insLabel(Label label);

    @Select("select count(*) from label where labelName = #{arg0}")
    int selByLabelName(String labelName);
}
