package com.qiang;

import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.ReportComment;
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
    private CommentMapper commentMapper;

    @Test
    public void contextLoads() {

        List<Comment> byBlogIdAndPid = commentMapper.findByBlogIdAndPid(1563105008L);
        for (Comment c:
             byBlogIdAndPid) {
            System.out.println(c.getMessage());
            Set<ReportComment> reportComments = c.getReportComments();
            for (ReportComment r:
                 reportComments) {
                System.out.println(r.getRepName());
            }
        }
    }

}
