package com.github.noodzhan.main.current;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 对于多个并发的任务的控制
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 5:52 下午
 */
public class CountDownLatchDemo2 {

    static class Task implements Runnable {
        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "执行任务");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":" + countDownLatch.getCount());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < count; i++) {
            new Thread(new Task(countDownLatch), "task" + i).start();
        }
        Thread.sleep(500);

        countDownLatch.await();
    }
}
