package com.github.noodzhan.main.current;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试wait方法
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/27 10:46 下午
 */
public class WaitFunctionDemo {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        WaitFunctionDemo.getAll("", 1);
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        List<String> generate = WaitFunctionDemo.generate(1);
        System.out.println(System.currentTimeMillis() - start1);

        System.out.println(generate.toString());


        Object lock = new Object();
        //是否能消费
        AtomicReference<Boolean> running = new AtomicReference<>(false);
        //十个消费者
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                synchronized (lock) {
                    //判断是否能消费
                    while (running.get()) {
                        System.out.println(finalI + "消费");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                        System.out.println(finalI + "阻塞");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(finalI + "继续消费");
                }
            }).start();
        }

        System.out.println("主线程等待5s");
        TimeUnit.SECONDS.sleep(5);

        //生产者
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    lock.notifyAll();
                    running.set(true);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }

        Thread.currentThread().join();
    }


    public static List<String> generate(int n) {
        List<String> result = new LinkedList<>();
        if (n == 1) {
            result.add("(");
            result.add(")");
        }else{
            List<String> generate = generate(n - 1);
            for (String s : generate) {
                result.add(s + "(");
                result.add(s + ")");
            }
        }
        return result;
    }


    /**
     * zhang
     * @param str
     * @param n
     * @return
     */
    public static List<String> getAll(String str, int n) {
        List<String> result = new LinkedList<>();
        if (n == 0){
            result.add(str);
            return result;
        }
        result.addAll(getAll(str + "(", n - 1));
        result.addAll(getAll(str + ")", n - 1));
        return result;
    }







}
