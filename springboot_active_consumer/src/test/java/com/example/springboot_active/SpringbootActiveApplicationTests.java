package com.example.springboot_active;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActiveApplicationTests {

    @Autowired
    private JmsMessagingTemplate jms;
//    @Autowired
//    private Producer producer;
    @Autowired
    private Queue queue;

    @Test
    public void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(queue, "queue"+i);
        }
    }
}
