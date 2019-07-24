package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.GuestMapper;
import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.GuestLikes;
import com.qiang.modules.sys.pojo.RepGuest;
import com.qiang.modules.sys.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service.impl
 * @Description: 留言业务逻辑层
 * @Date: 2019/7/24 0024 17:31
 **/
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestMapper guestMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Guest> getAllGuest() {
        return guestMapper.getAllGuest();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean findIsLikes(GuestLikes guestLikes) {
        return guestMapper.findIsLikes(guestLikes) == 1 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delIsLikes(GuestLikes guestLikes) {
        return guestMapper.delIsLike(guestLikes);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updInsLikes(Long id) {
        return guestMapper.updInsRepGuest(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Guest> insIsLikes(GuestLikes guestLikes) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        guestLikes.setId(id);
        int i = guestMapper.insIsLikes(guestLikes);
        int k = updInsLikes(guestLikes.getGuestId());
        List<Guest> list = null;
        if(i > 0 && k > 0){
            list = getAllGuest();
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Guest> updDesLikes(Long id) {
        int i = guestMapper.updDesRepGuest(id);
        List<Guest> list = null;
        if(i > 0){
            list = getAllGuest();
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Guest> insRepGuest(RepGuest repGuest) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        repGuest.setRid(id);
        repGuest.setRcreateTime(timeUtil.getParseDateForSix());
        repGuest.setRisRead(1);
        List<Guest> list = null;
        int i = guestMapper.insRepGuest(repGuest);

        if(i > 0){
            list = getAllGuest();
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Guest> insGuest(Guest guest) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        guest.setId(id);
        guest.setCreateTime(timeUtil.getParseDateForSix());
        guest.setLikes(0);
        guest.setIsRead(1);
        List<Guest> list = null;
        int i = guestMapper.insGuest(guest);
        if(i > 0){
            list = getAllGuest();
        }
        return list;
    }
}
