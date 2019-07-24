package com.qiang;

import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.mapper.GuestMapper;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.Guest;
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

    @Autowired
    private GuestMapper guestMapper;
    @Test
    public void contextLoads() {

        List<Guest> allGuest = guestMapper.getAllGuest();
        allGuest.forEach(System.out::println);

    }

}
