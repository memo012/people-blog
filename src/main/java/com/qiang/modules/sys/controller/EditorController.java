package com.qiang.modules.sys.controller;

import com.qiang.common.utils.*;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import com.qiang.modules.sys.service.BlogService;
import com.qiang.modules.sys.service.LabelService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 编辑文章
 * @Date: 2019/7/4 0004 11:05
 **/
@RestController
public class EditorController {

    @Value("${qiang.file-path}")
    private String fileSpace;


    @Autowired
    private LabelService labelService;

    @Autowired
    private BlogService blogService;

    /**
     * 发布博客
     * @param blogMessage
     * @param request
     * @return
     */
    @PostMapping("publishEditor")
    public BlogJSONResult publishEditor(@RequestBody BlogMessageVO blogMessage
            , HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorTokenMsg("用户已过期");
        }
        // 生成文章摘要
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        String articleHtmlContent = buildArticleTabloidUtil.buildArticleTabloid(blogMessage.getArticleHtmlContent());
        blogMessage.setArticleTabloid(articleHtmlContent + "...");

        //标签
        String[] tagValue = blogMessage.getTagValue();
        for (int i = 0; i < tagValue.length; i++) {
            //去掉可能出现的换行符
            tagValue[i] = tagValue[i].replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("\\s+", "").trim();
        }
        labelService.insLabel(tagValue);
        String label = StringAndArray.arrayToString(tagValue);
        blogMessage.setLabelValues(label);
        blogMessage.setName(user.getUsername());
        blogMessage.setOriginalAuthor("");

        String id = request.getParameter("id");

        //修改文章
        if (!"".equals(id) && id != null) {

        }
        blogService.publishBlog(blogMessage);
        return BlogJSONResult.ok(blogMessage);
    }

    @GetMapping("/isNotPermission")
    public BlogJSONResult isNotPermission(){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.hasRole("admin")){
            return BlogJSONResult.errorRolesMsg("无角色功能");
        }
        return BlogJSONResult.ok();
    }

    /**
     * 上传图片
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                                          HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        Users user = (Users) request.getSession().getAttribute("user");
        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis()+"_"+ CommonUtils.getRandomNumber(100, 999)+suffix;
        //保存到数据库的相对路径
        String uploadPathDB = "/" + user.getId() + "/face";
        //文件上传的最终保存路径
        String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
        String fileRealPath = uploadPathDB + "/" + fileName;
        try{
            FileUtil.uploadFile(file.getInputStream(), finalFacePath);
            map.put("url", fileRealPath);
            map.put("success", 1);
            map.put("message", "upload success!");
        }catch (Exception e){
        }

        return map;

    }
}
