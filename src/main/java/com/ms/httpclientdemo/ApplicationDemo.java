package com.ms.httpclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wuketao on 2017/11/15.
 */
@SpringBootApplication
@EnableSwagger2
public class ApplicationDemo {
    public static void main(String[] args){
        SpringApplication.run(ApplicationDemo.class,args);
    }
}
