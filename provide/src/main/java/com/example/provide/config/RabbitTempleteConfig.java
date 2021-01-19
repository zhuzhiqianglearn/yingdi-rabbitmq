package com.example.provide.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTempleteConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate=new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
                if(ack){
                    System.out.println("发送成功-exchange");
                }else{
                    System.out.println("发送失败-exchange");
                }
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("ReturnCallback:     "+"消息："+returned.getMessage());
                System.out.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
                System.out.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
                System.out.println("ReturnCallback:     "+"交换机："+returned.getExchange());
                System.out.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
                if(returned.getReplyCode()==200){
                    System.out.println("发送成功-queue");
                }else{
                    System.out.println("发送失败-queue");
                }
            }
        });
        return  rabbitTemplate;
    }
}
