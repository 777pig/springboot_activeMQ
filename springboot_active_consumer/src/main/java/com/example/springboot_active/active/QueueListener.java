package com.example.springboot_active.active;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消费者的监听器，可以监听生产到队列和话题中
 * @author: elvin
 */
@Component
public class QueueListener {

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")
    public String receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }

    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void topic_(String text){
        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
    }
}