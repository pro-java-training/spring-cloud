package com.codve.schedule;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @date 2019/12/18 14:25
 */
public class CountDownLatchTest {

    @Test
    void test() {
        int num = 10;
        ExecutorService executor = Executors.newFixedThreadPool(num);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            });
        }
        executor.shutdown();
        System.out.println("主线程执行完毕");
    }

//    public static void main(String[] args) {
//
//    }


    @Test
    void testThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                PrintWriter writer = new PrintWriter("log.txt");
                writer.println(Thread.currentThread().getName() + " 执行完成");
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        System.out.println("主线程执行完毕");
    }

    @Test
    void testDaemon() {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10000; i++) {
                    PrintWriter writer = new PrintWriter("log.txt");
                    writer.println(Thread.currentThread().getName() + " 执行中");
                    writer.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        System.out.println(Thread.currentThread().getName() + " 执行完毕");
    }

    @Test
    void testJoin() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " 执行中");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread().getName() + " 执行完毕");
    }

    @Test
    void testMultiJoin() throws InterruptedException {
        class SubThread extends Thread {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " 执行中");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        long start = System.currentTimeMillis();
        SubThread s1 = new SubThread();
        SubThread s2 = new SubThread();
        s1.start();
        s1.join();
        s2.start();
        s2.join();
        long end = (System.currentTimeMillis() - start) / 1000;
        System.out.println(end + " s");
    }

    class Worker extends Thread{
        private CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " 执行完成");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        }
    }

    @Test()
    void testCountDownLatch() throws InterruptedException {
        int num = 5;
        CountDownLatch latch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker(latch);
            worker.start();
        }
        latch.await();
    }
}
