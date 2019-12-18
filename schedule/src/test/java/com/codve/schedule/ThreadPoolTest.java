package com.codve.schedule;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author admin
 * @date 2019/12/18 16:37
 */
public class ThreadPoolTest {

    class Worker extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " 执行完毕");
        }
    }

    @Test
    void testSingle() throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2 );
         service.scheduleAtFixedRate(new Worker(), 0L, 1L, TimeUnit.SECONDS);
        while (!service.awaitTermination(3, TimeUnit.SECONDS)) {

        }
    }

    @Test
    void testThreadPoll() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));

        for (int i = 0; i < 10; i++) {
            executor.execute(new Worker());
        }
        executor.shutdown();
        while (!executor.awaitTermination(3, TimeUnit.SECONDS)) {

        }
    }
}
