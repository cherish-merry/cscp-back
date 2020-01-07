//package com.cscp.userServer.service.impl;
//
//import com.cscp.userServer.dao.entity.Menu;
//import com.cscp.userServer.service.IMenuService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDateTime;
//
///**
// * @author chen kezhuo
// * @discription
// * @date 2019/8/23 - 11:20
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserServiceImplTest {
//    @Autowired
//    AmqpTemplate amqpTemplate;
//
//    @Autowired
//    IMenuService iMenuService;
//
//    @Test
//    public void testMessage(){
//        amqpTemplate.convertAndSend("myQueue","time:"+ LocalDateTime.now());
//    }
//
//    @Test
//    public void testMessage2(){
//        amqpTemplate.convertAndSend("myExchange","queue2","time:"+ LocalDateTime.now());
//    }
//
//    @Test
//    public void testMenu(){
//        for (Menu menu : iMenuService.getMenusByUsername("ckz")) {
//            System.out.println(menu.getName());
//        }
//    }
//}