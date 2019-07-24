package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.GuestLikes;
import com.qiang.modules.sys.pojo.RepGuest;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 留言controller
 * @Date: 2019/7/24 0024 17:00
 **/
@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    /**
     * 获取全部留言
     * @return
     */
    @GetMapping("/getAllGuest")
    public BlogJSONResult getAllGuest(){
        List<Guest> allGuest = guestService.getAllGuest();
        if(allGuest != null){
            return BlogJSONResult.ok(allGuest);
        }else{
            return BlogJSONResult.errorMsg("无果");
        }
    }

    /**
     * 新增留言
     * @param guest
     * @param request
     * @return
     */
    @PostMapping("/insGuest")
    public BlogJSONResult insGuest(@RequestBody Guest guest,
                                   HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorTokenMsg("用户已过期");
        }
        guest.setUserId(user.getId());
        guest.setAuthorName(user.getUsername());
        List<Guest> guests = guestService.insGuest(guest);
        return BlogJSONResult.ok(guests);
    }

    /**
     * 新增留言评论
     * @param repGuest
     * @param request
     * @return
     */
    @PostMapping("/insRepGuest")
    public BlogJSONResult insRepGuest(@RequestBody RepGuest repGuest,
                                      HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorTokenMsg("用户已过期");
        }
        repGuest.setRguestId(user.getId());
        repGuest.setRepName(user.getUsername());
        List<Guest> guests = guestService.insRepGuest(repGuest);
        if(guests != null){
            return BlogJSONResult.ok(guests);
        }else{
            return BlogJSONResult.errorMsg("新增失败");
        }
    }

    /**
     * 点赞更新
     * @param guestLikes
     * @param request
     * @return
     */
    @PostMapping("/updGuestLikes")
    public BlogJSONResult updGuestLikes(@RequestBody GuestLikes guestLikes,
                                        HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorTokenMsg("用户已过期");
        }
        guestLikes.setLikeName(user.getUsername());
        boolean isLikes = guestService.findIsLikes(guestLikes);
        if(isLikes){
            // 已点过赞
            List<Guest> guests = guestService.updDesLikes(guestLikes.getGuestId());
            if(guests != null){
                guestService.delIsLikes(guestLikes);
                return BlogJSONResult.ok(guests);
            }else{
                // 点赞失败
                return BlogJSONResult.errorMsg("点赞失败");
            }
        }else{
            // 未点过赞
            List<Guest> guests = guestService.insIsLikes(guestLikes);
            if(guests != null){
                // 点赞成功
                return  BlogJSONResult.ok(guests);
            }else{
                // 点赞失败
                return BlogJSONResult.errorMsg("点赞失败");
            }
        }
    }

}
