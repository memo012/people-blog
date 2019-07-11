package com.qiang.mapper;

import com.qiang.pojo.Users;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.mapper
 * @Description:
 * @Date: 2019/6/20 0020 21:30
 **/
@Repository
public interface UsersMapper {

    @Select("select * from user where phone = #{arg0}")
    Users findByPhone(String phone);
}
