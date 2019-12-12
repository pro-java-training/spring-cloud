package com.codve.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/11/19 13:13
 */
@Configuration
@MapperScan(basePackages = "com.codve.user.dao")
public class MybatisConfig {

}
