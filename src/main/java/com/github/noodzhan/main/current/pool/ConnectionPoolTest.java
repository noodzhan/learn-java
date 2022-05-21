package com.github.noodzhan.main.current.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池，测试类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @see ConnectionPool
 * @since 2021/7/2 10:58 上午
 */
public class ConnectionPoolTest {

    static int threadCount = 100;

    static CountDownLatch startSignal = new CountDownLatch(1);

    static ConnectionPool connectionPool = new ConnectionPool(10);

    static CountDownLatch endSignal = new CountDownLatch(threadCount);

    static class ConnectionRunnable implements Runnable {
        private AtomicInteger got;
        private AtomicInteger unGot;
        //获取连接总数
        private int count;

        public ConnectionRunnable(AtomicInteger got, AtomicInteger unGot, int count) {
            this.got = got;
            this.unGot = unGot;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                Connection connection = null;
                try {
                    connection = connectionPool.fetchConnection(100);
                    if (connection != null) {
                        connection.createStatement();
                        connection.commit();
                        got.getAndIncrement();
                    }else{
                        unGot.getAndIncrement();
                    }
                } catch (Exception exception) {
                } finally {
                    connectionPool.releaseConnection(connection);
                }
                count--;
            }
            endSignal.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException, SQLException {
        //确定获取连接总数
        AtomicInteger got = new AtomicInteger();
        //确定没有获取连接总数
        AtomicInteger unGot = new AtomicInteger();
        //预计获取连接总数
        int count = 100;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new ConnectionRunnable(got, unGot, count), "Thread" + i).start();
        }
        //多个线程同时的去从连接池获取连接
        startSignal.countDown();
        endSignal.await();

        System.out.println("每个线程单次获取的连接数："+count);
        System.out.println("当前"+threadCount+"线程获取的总数为："+got);
        System.out.println("当前"+threadCount+"线程未获取的总数为："+unGot);
    }
}
