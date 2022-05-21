package com.github.noodzhan.main.current;

import java.util.concurrent.*;

/**
 *
 * futureTask测试
 * @see java.util.concurrent.FutureTask
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 4:06 下午
 */
public class FutureTaskDemo {

    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Integer> submit = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return 1;
        });
        Integer integer = submit.get();
        System.out.println(integer);
        executorService.shutdown();
    }


}
