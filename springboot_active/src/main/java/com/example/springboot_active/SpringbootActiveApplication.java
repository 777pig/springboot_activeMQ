package com.example.springboot_active;

import com.example.springboot_active.active.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 生产者，生产数据
 * http://localhost:8081/active/publish/queue
 * 队列形式发送数据
 */
@SpringBootApplication
public class SpringbootActiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActiveApplication.class, args);
    }

}
