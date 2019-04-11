package com.example.springboot_active;

import com.example.springboot_active.text_info.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActiveApplicationTests {

    @Autowired
    private JmsMessagingTemplate jms;
//    @Autowired
//    private Producer producer;
    @Autowired
    private Text d;

    @Test
    public void contextLoads() throws Exception{
        System.out.println(d);
    }
}
