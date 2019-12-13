package com.codve.article;

import com.codve.article.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = RibbonConfig.class)
})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
