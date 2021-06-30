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
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 超时机制的获取task值
     * @param ms 毫秒
     * @return
     */
    public int executeTaskHasTimeOut(long ms)  {
        long current = System.currentTimeMillis();
        long future = current + ms;
        int result = 0;
        while (future - current >= 0) {
            //开启一个业务线程，去执行任务，获取值。
            new Thread(()->{
                int task = this.task();
                System.out.println(task);
            },"task").start();
            try {
                this.wait(future - current);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = System.currentTimeMillis();
        }
        return result;

    }


    public static void main(String[] args) {
        WaitTimeOutDemo waitTimeOutDemo = new WaitTimeOutDemo();
        System.out.println(waitTimeOutDemo.executeTaskHasTimeOut(500));

    }

}
