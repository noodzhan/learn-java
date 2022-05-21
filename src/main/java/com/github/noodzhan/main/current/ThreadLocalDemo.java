package com.github.noodzhan.main.current;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试ThreadLocal
 * @see ThreadLocal
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/4 4:39 下午
 */
public class ThreadLocalDemo {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
    //这里面是初始化，主线程的hashmap
    static{
        threadLocal.set(new ConcurrentHashMap<>());
    }
    public static ExecutorService executor = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.submit(()->{
                Map<String, Object> stringObjectMap = threadLocal.get();
                if (Objects.isNull(stringObjectMap)) {
                    threadLocal.set(new ConcurrentHashMap<>());
                }
                stringObjectMap.put("key", new String("测试"));
                System.out.println(Thread.currentThread().getName() + threadLocal.get().get("key"));
            });
        }
        Object key = threadLocal.get().get("key");
        System.out.println(key==null?"null":key);
        executor.shutdown();
    }



}
