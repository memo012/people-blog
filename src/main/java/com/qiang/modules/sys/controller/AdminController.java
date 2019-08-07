package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.common.utils.PagedResult;
import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.service.AdminService;
import com.qiang.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 后台管理者controller
 * @Date: 2019/8/2 0002 17:53
 **/
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * 全部用户信息
     * @return
     */
    @GetMapping("getUsers")
    public BlogJSONResult getUserS(@RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            PagedResult allUsers = adminService.findAllUsers(pageSize, pageNum);
            return BlogJSONResult.ok(allUsers);
        }else{
            return BlogJSONResult.errorRolesMsg("无权限");
        }
    }

    /**
     * 全部博客信息
     * @return
     */
    @GetMapping("getBlogs")
    public BlogJSONResult getBlogs(@RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            PagedResult allUsers = adminService.findAllBlogs(pageSize, pageNum);
            return BlogJSONResult.ok(allUsers);
        }else{
            return BlogJSONResult.errorRolesMsg("无权限");
        }
    }

    /**
     * 删除用户
     * @param username
     * @return
     */
    @GetMapping("delUsers")
    public BlogJSONResult delUsers(@RequestParam(value = "username") String username){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            adminService.deleteUsers(username);
            return BlogJSONResult.ok();
        }else{
            return BlogJSONResult.errorRolesMsg("无权限");
        }
    }


    /**
     * 删除博客
     * @param id
     * @return
     */
    @GetMapping("delBlog")
    public BlogJSONResult delBlog(@RequestParam(value = "id") Long id){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            adminService.deleteBlog(id);
            return BlogJSONResult.ok();
        }else{
            return BlogJSONResult.errorRolesMsg("无权限");
        }
    }


    /**
     * 反馈(管理员)
     * @param username
     * @return
     */
    @GetMapping("messageNotReadMana")
    public BlogJSONResult messageNotReadMana(@RequestParam("username") String username){
        int messageNotRead = userService.findMessageNotRead(username);
        return BlogJSONResult.ok(messageNotRead);
    }


    /**
     * 我的留言（管理员）
     * @return
     */
    @GetMapping("getNotAllGuest")
    public BlogJSONResult getAllGuest(){
        List<Guest> allGuest = userService.findAllGuest();
        return BlogJSONResult.ok(allGuest);

    }

    /**
     * 部分留言已读（管理员）
     * @param id 留言id
     * @return
     */
    @GetMapping("clearFirstNotGuestMana")
    public BlogJSONResult clearFirstNotGuestMana(@RequestParam("id") Long id){
        int i = userService.updOneNotGuestMana(id);
        return BlogJSONResult.ok();
    }

}
