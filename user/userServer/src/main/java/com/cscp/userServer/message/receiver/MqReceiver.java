//package com.cscp.userServer.message.receiver;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author chen kezhuo
// * @discription
// * @date 2019/8/24 - 13:04
// */
//@Component
//@Slf4j
//public class MqReceiver {
////    @RabbitListener(queues = "myQueue")
//    //自动创建队列
////    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    //绑定exchange
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("myQueue"),
//            exchange = @Exchange("myExchange")
//    ))
//    public void process(String message){
//        log.info(message);
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("queue1"),
//            key = "queue1",
//            exchange = @Exchange("myExchange")
//    ))
//    public void process1(String message){
//        log.info("queue1"+message);
//    }
//
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("queue2"),
//            key = "queue2",
//            exchange = @Exchange("myExchange")
//    ))
//    public void process2(String message){
//        log.info("queue2"+message);
//    }
//
//}
