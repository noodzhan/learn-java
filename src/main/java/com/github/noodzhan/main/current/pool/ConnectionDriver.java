package com.github.noodzhan.main.current.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 连接驱动
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 4:48 下午
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                System.out.println(Thread.currentThread().getName()+"执行commit");
                Random random = new Random(System.currentTimeMillis());
                int randomInt = random.nextInt(5);
                TimeUnit.SECONDS.sleep(randomInt);
            }
            return null;
        }
    }

    public static final Connection createConnection(){
        return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionHandler());
    }
}
