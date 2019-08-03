package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.UsersMapper;
import com.qiang.modules.sys.pojo.*;
import com.qiang.modules.sys.service.UserService;
import com.qiang.modules.sys.shiro.ShiroMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<RepGuest> updNotGuest(String username) {
        List<Long> id = usersMapper.findNotGuest(username);
        List<RepGuest> list = null;
        if(id.size() == 0){
            return list;
        }
        int i = usersMapper.updGuestIsRead(id);
        if(i > 0){
            list = findNotRepReadGuest(username);
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updOneBlogNotLikes(Long id) {
        return usersMapper.updOneBlogNotLikes(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updOneBlogNotComm(Long id) {
        return usersMapper.updOneBlogNotComm(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findMessageNotReadUser(String username) {
        int c2 = usersMapper.findNotReadRepComm(username);
        int c4 = usersMapper.findNotReadRepGuest(username);
        return c2 + c4;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findMessageNotRead(String username) {
        int c1 = usersMapper.findNotReadComm();
        int c2 = usersMapper.findNotReadRepComm(username);
        int c3 = usersMapper.findNotReadGuest();
        int c4 = usersMapper.findNotReadRepGuest(username);
        return c1 + c2 + c3 + c4;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> findAllComment() {
        return usersMapper.findAllComment();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updOneNotGuestUser(Long id) {
        return usersMapper.updOneNotGuestUser(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updOneNotGuestMana(Long id) {
        return usersMapper.updOneNotGuestMana(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<RepGuest> findNotRepReadGuest(String username) {
        return usersMapper.findNotRepReadGuest(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Guest> findAllGuest() {
        return usersMapper.findAllGuest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<CommentLikes> updComIsRead(String username) {
        List<Long> id = usersMapper.findNotReadLikesIdByUsername(username);
        List<CommentLikes> list = null;
        if(id.size() == 0){
            return list;
        }
        int i = usersMapper.updLikesIsRead(id);
        if(i > 0){
            list  = findLikes(username);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CommentLikes> findLikes(String username) {
        return usersMapper.findLikes(username);
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
