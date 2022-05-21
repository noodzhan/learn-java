package com.github.noodzhan.main.current;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch测试用例，主要测试作为任务启动信号方式
 * @see java.util.concurrent.CountDownLatch
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 5:45 下午
 */
public class CountDownLatchDemo {
    /**
     * 开始信号
     */
    private static  CountDownLatch startSignal = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Task(startSignal), "task1").start();
        new Thread(new Task(startSignal), "task2").start();
        TimeUnit.SECONDS.sleep(5);
        startSignal.countDown();
    }
    static class Task implements Runnable{
        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行任务");
        }
    }
}
