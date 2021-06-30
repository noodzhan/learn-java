package com.github.noodzhan.main.current;

import java.util.concurrent.TimeUnit;

/**
 * Join函数的使用实例
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/30 4:53 下午
 */
public class JoinFunctionDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1开始");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("t1结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2开始");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2等待t1");
                t1.join();//等待
                System.out.println("t2结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();


    }
}
