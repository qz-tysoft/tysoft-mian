package com.tysoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hxx
 * 服务启动类
 */
@SpringBootApplication
@MapperScan("com.tysoft.api.mapper.*")
public class TysoftApiAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftApiAppliacation.class, args);
    }
}
