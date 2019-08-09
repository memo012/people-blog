package com.qiang.modules.sys.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.mapper
 * @Description:
 * @Date: 2019/8/9 0009 17:33
 **/
@Repository
public interface RegisterMapper {

    @Select("select count(*) from users where phone = #{arg0}")
    int findByPhone(String phone);

    @Select("select count(*) from users where username = #{arg0}")
    int findByName(String username);

}
