package com.qiang.service.impl;

import com.qiang.mapper.UsersMapper;
import com.qiang.pojo.Users;
import com.qiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service.impl
 * @Description:
 * @Date: 2019/6/20 0020 11:26
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findByPhone(String phone) {
        Users byPhone = usersMapper.findByPhone(phone);
        return byPhone;
    }
}
