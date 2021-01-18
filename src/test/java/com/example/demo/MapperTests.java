package com.example.demo;

import com.example.demo.dao.DiscussPostMapper;
import com.example.demo.dao.LoginTicketMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.DiscussPost;
import com.example.demo.entity.LoginTicket;
import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testSelectUser(){
        User user = usermapper.selectById(101);
        System.out.println(user);
        user = usermapper.selectByName("liubei");
        System.out.println(user);
        user = usermapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("1234");
        user.setSalt("abc");
        user.setEmail("123@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows =usermapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void updateUser(){
        int rows = usermapper.updateStatus(150,1);
        System.out.println(rows);
        rows = usermapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        System.out.println(rows);
        rows = usermapper.updatePassword(150,"7898");
        System.out.println(rows);
    }
    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for(DiscussPost post:list){
            System.out.println(post);
        }
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);

    }
    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket("abc");
        loginTicket.setUserId(123);
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60) );

        loginTicketMapper.insertLoginTicket(loginTicket);
    }
}
