package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.UsersMapper;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.service.UserService;
import com.qiang.modules.sys.shiro.ShiroMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users insUserMess(Users users) {
        int i = usersMapper.updUserMess(users);
        Users u = null;
        if(i > 0){
            u = findUserMess(users.getUsername());
        }
        return u;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insUsers(Users users) {
        String id = UUID.randomUUID().toString();
        users.setId(id);
        users.setRoleId(2);
        Object o = ShiroMD5.MD5(users.getPhone(), users.getPassword());
        users.setPassword(String.valueOf(o));
        String data = new TimeUtil().getFormatDateForThree();
        users.setLastTime(data);
        return usersMapper.insUsers(users);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByPhonePass(String phone, String password) {
        return usersMapper.findByPhonePass(phone, password);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findByName(String name) {
        return usersMapper.findByName(name);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findUserMess(String username) {
        return usersMapper.findUserMess(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findByPhone(String phone) {
        Users byPhone = usersMapper.findByPhone(phone);
        return byPhone;
    }
}
