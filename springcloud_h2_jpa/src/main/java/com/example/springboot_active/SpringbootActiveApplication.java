package com.example.springboot_active;

import com.example.springboot_active.active.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 生产者，生产数据
 * ws://localhost:8080/websocket/1/1
 * socket工具
 * 队列形式发送数据
 */
@SpringBootApplication
public class SpringbootActiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActiveApplication.class, args);
    }


}
