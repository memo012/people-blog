package com.qiang;

import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.phoneVerify.service.SMSService;
import com.qiang.common.utils.phoneVerify.util.SMSUtil;
import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.mapper.GuestMapper;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.Guest;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import com.qiang.modules.sys.service.CommentService;
import com.qiang.modules.sys.service.EsService;
import com.qiang.modules.sys.service.UserService;
import com.qiang.modules.sys.service.impl.EsServiceImpl;
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

    @Test
    public void contextLoads() {
    }

}
