package com.example.consummer.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.man")
public class TopicReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("topic.man消费者收到消息  : " + testMessage.toString());
    }

}
