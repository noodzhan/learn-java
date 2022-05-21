package com.github.noodzhan.main.current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 4:21 下午
 */
public class ThreadPoolDemo {
    public static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        pool.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
        pool.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second task");

        });
//        pool.shutdown();
    }
}
