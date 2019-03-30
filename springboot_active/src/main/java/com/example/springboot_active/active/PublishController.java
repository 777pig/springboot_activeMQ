package com.example.springboot_active.active;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author: elvin
 * topic 数据消费不到之前没有消费的，二queue可以消费之前消费不到的
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/queue")
    public String queue(){
        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(queue, "queue"+i);
        }
        System.out.println("测试2");
        return "index";
    }

    /**
     * 监听发送的请求，这里的queueName是队列的名字
     * @param msg
     */
    @JmsListener(destination = "${queueName}")
    public void consumerMsg(String msg){
        System.out.println(msg);
    }

    @RequestMapping("/topic")
    public String topic(){

        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(topic, "topic"+i);
        }

        return "topic 发送成功";
    }
}