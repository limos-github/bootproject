package com.limo.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author lib97
 */
@MapperScan("com.limo.boot.mapper")
@SpringBootApplication
public class MybatisMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMysqlApplication.class, args);
    }

}
