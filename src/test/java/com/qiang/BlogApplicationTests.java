package com.qiang;

import com.qiang.common.utils.RedisOperator;
import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.mapper.GuestMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import com.qiang.modules.sys.service.CommentService;
import com.qiang.modules.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {

        BlogMessage blogMessage = new BlogMessage();
        blogMessage.setId(1344444446L);
        blogMessage.setLike(1);
//        long lpush = redisOperator.lpush("redis:ff", blogMessage);

        long lremove = redisOperator.lremove("redis:ff", 0, blogMessage);
        System.out.println(lremove);

//        List<BlogMessage> range =  (List<BlogMessage>)redisOperator.range("redis:ff", 0, 3);
//        for (BlogMessage b:
//             range) {
//            System.out.println(b.getId());
//        }
    }

}
