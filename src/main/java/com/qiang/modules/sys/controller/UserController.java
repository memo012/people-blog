package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.modules.sys.pojo.*;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import com.qiang.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 用户管理
 * @Date: 2019/7/14 0014 16:05
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param users
     * @return
     */
    @PostMapping("register")
    public BlogJSONResult register(@RequestBody Users users){
        int result = userService.insUsers(users);
        if(result > 0){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("注册失败");
    }

    @RequestMapping("admin")
    public String geAdim(){
        return "admin";
    }


    /**
     * 手机号检测
     * @param phone
     * @return
     */
    @GetMapping("phoneCheck")
    public BlogJSONResult phoneCheck(@RequestParam("phone") String phone){
        Users result = userService.findByPhone(phone);
        if(result == null){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("该手机号已被注册");
    }

    /**
     * 用户检测
     * @param username
     * @return
     */
    @GetMapping("usernameCheck")
    public BlogJSONResult usernameCheck(@RequestParam("username") String username){
        Users result = userService.findByName(username);
        if(result == null){
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("该用户已存在");
    }

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @param session
     * @return
     */
    @GetMapping("loginUser")
    public BlogJSONResult loginUser(@RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
//        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            Users user = (Users)subject.getPrincipal();
            user.setPassword("");
            System.out.println(user.getRoles());
            session.setAttribute("user", user);
            return BlogJSONResult.ok(user);
        }catch (AuthenticationException e){
            return BlogJSONResult.errorMsg("输入的用户名或密码错误");
        }
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("logout")
    public BlogJSONResult logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            request.getSession().invalidate();
            subject.logout();
        }
        return BlogJSONResult.ok();
    }

    /**
     * 该用户是否过期
     * @param request
     * @return
     */
    @GetMapping("/isLogin")
    public BlogJSONResult isLogin(HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorMsg("用户已过期");
        }
        return BlogJSONResult.ok();
    }

    /**
     * 获取个人信息
     * @param username
     * @return
     */
    @GetMapping("getUserMess")
    public BlogJSONResult getUserMess(@RequestParam("username") String username){
        Users userMess = userService.findUserMess(username);
        return BlogJSONResult.ok(userMess);
    }

    /**
     * 新增个人信息
     * @param users
     * @return
     */
    @PostMapping("/insUserMess")
    public BlogJSONResult insUserMess(@RequestBody Users users){
        Users users1 = userService.insUserMess(users);
        if(users1 != null){
            return BlogJSONResult.ok(users1);
        }else{
            return BlogJSONResult.errorMsg("修改失败");
        }
    }

    /**
     * 我的点赞
     * @return
     */
    @GetMapping("getUserLikes")
    public BlogJSONResult getUserLikes(@RequestParam("username") String username){
        List<CommentLikes> likes = userService.findLikes(username);
        return BlogJSONResult.ok(likes);
    }

    /**
     * 全部点赞消息已读
     * @param username
     * @return
     */
    @GetMapping("clearLikeNotRead")
    public BlogJSONResult clearLikeNotRead(@RequestParam("username") String username){
        List<CommentLikes> reportCommentVOS = userService.updComIsRead(username);
        if(reportCommentVOS != null){
            return BlogJSONResult.ok(reportCommentVOS);
        }
        return BlogJSONResult.errorMsg("错误");
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

    /**
     * 我的留言（用户）
     * @return
     */
    @GetMapping("getUserGuest")
    public BlogJSONResult getUserGuest(@RequestParam("username") String username){
        List<RepGuest> notRepReadGuest = userService.findNotRepReadGuest(username);
        return BlogJSONResult.ok(notRepReadGuest);
    }


    /**
     * 部分留言已读（管理员）
     * @param id 留言id
     * @return
     */
    @GetMapping("clearFirstNotGuestUser")
    public BlogJSONResult clearFirstNotGuestUser(@RequestParam("id") Long id){
        int i = userService.updOneNotGuestUser(id);
        return BlogJSONResult.ok();
    }


    /**
     * 全部留言消息已读（用户）
     * @param username
     * @return
     */
    @GetMapping("clearNotGuest")
    public BlogJSONResult clearNotGuest(@RequestParam("username") String username){
        List<RepGuest> reportCommentVOS = userService.updNotGuest(username);
        if(reportCommentVOS != null){
            return BlogJSONResult.ok(reportCommentVOS);
        }
        return BlogJSONResult.errorMsg("错误");
    }


    /**
     * 我的评论（博客）
     * @return
     */
    @GetMapping("getBlogUserReport")
    public BlogJSONResult getBlogUserReport(){
        List<Comment> allComment = userService.findAllComment();
        return BlogJSONResult.ok(allComment);
    }


    /**
     *  部分博客评论消息已读
     * @return
     */
    @GetMapping("clearOneBlogNotComm")
    public BlogJSONResult clearOneBlogNotComm(@RequestParam("id") Long id){
        userService.updOneBlogNotComm(id);
        return BlogJSONResult.ok();
    }


    /**
     *  部分博客评论消息已读
     * @return
     */
    @GetMapping("clearOneBlogNotLikes")
    public BlogJSONResult clearOneBlogNotLikes(@RequestParam("id") Long id){
        userService.updOneBlogNotLikes(id);
        return BlogJSONResult.ok();
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
     * 消息通信(用户)
     * @param username
     * @return
     */
    @GetMapping("messageNotReadUser")
    public BlogJSONResult messageNotReadUser(@RequestParam("username") String username){
        int messageNotRead = userService.findMessageNotReadUser(username);
        return BlogJSONResult.ok(messageNotRead);
    }



}
