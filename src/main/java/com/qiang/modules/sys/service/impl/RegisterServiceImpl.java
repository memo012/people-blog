package com.qiang.modules.sys.service.impl;

import com.qiang.modules.sys.mapper.RegisterMapper;
import com.qiang.modules.sys.service.AsyncService;
import com.qiang.modules.sys.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service.impl
 * @Description:
 * @Date: 2019/8/9 0009 17:32
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private AsyncService asyncService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByPhone(String phone) {
        int phoneNum = registerMapper.findByPhone(phone);
        // 异步把数据库中的手机号存入缓存
        asyncService.insUserPhone();
        return phoneNum;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByUsername(String username) {
        int name = registerMapper.findByName(username);
        // 异步把数据库中的用户名存入缓存
        asyncService.insUserName();
        return name;
    }
}
