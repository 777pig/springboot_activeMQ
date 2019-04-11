package com.example.springboot_active.text_info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@PropertySource(value={"classpath:Text.properties"},encoding="GBK")
@ConfigurationProperties(prefix = "whx")
@Component
public class Text {
    @Override
    public String toString() {
        return "Text{" +
                "str='" + str + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    //    @Value("${str}")
    private String str;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @Value("${name}")
    private String name;
}