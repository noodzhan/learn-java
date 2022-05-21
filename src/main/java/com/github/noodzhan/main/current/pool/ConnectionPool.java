package com.github.noodzhan.main.current.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 模拟连接池技术
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 4:45 下午
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();


    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                //通知阻塞到当前连接池的线程
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    this.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                //不满足条件，就等待
                while (pool.isEmpty() && remaining > 0) {
                    this.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                //满足条件，继续判断一下，然后返回
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }

        }
    }

}
