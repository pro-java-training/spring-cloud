package com.codve.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class ScheduleApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ScheduleApplication.class, args);
		new CountDownLatch(1).await();
	}
}
