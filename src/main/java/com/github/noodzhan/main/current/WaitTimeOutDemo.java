package com.github.noodzhan.main.current;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 等待超时机制
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/30 5:40 下午
 */
public class WaitTimeOutDemo {

    /**
     * 任务抽象
     * @return 任务值
     */
    public int task(){
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("task is running");
                TimeUnit.SECONDS.sleep(5);
                return 2;
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            throw new RuntimeException("中断异常");
        }finally {
            System.out.println("finally");
        }
        return 1;
    }

    /**
     * 超时机制的获取task值
     * @param ms 毫秒
     * @return
     */
    public synchronized int executeTaskHasTimeOut(long ms)  {
        long current = System.currentTimeMillis();
        long future = current + ms;
        int result = 0;
        Thread threadTask = new Thread(() -> {
            int task = this.task();
            System.out.println("执行task任务，结果为"+task);
        }, "task");
        while (future - current >= 0) {
            //开启一个业务线程，去执行任务，获取值。
            threadTask.start();
            try {
                this.wait(future - current);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = System.currentTimeMillis();
        }
        threadTask.interrupt();
        return result;

    }


    public static void main(String[] args) {
        WaitTimeOutDemo waitTimeOutDemo = new WaitTimeOutDemo();
        System.out.println(waitTimeOutDemo.executeTaskHasTimeOut(500));

    }

}
