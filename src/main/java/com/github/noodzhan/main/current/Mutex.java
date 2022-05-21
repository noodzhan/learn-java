package com.github.noodzhan.main.current;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现互斥锁
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/7 5:04 下午
 */
public class Mutex implements Lock {

    private final Sync sync = new Sync();

    //静态内部类
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            return this.compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return this.compareAndSetState(1, 0);
        }

    }
    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    static class TRun implements Runnable{
        private volatile int count;
        private final CountDownLatch countDownLatch;
        private final Mutex mutex;

        public TRun(int count, CountDownLatch countDownLatch, Mutex mutex) {
            this.count = count;
            this.countDownLatch = countDownLatch;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId()+":"+(--count));
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        int count = 10;
        Mutex mutex = new Mutex();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new TRun(count,countDownLatch, mutex)).start();
        }
        countDownLatch.countDown();
    }
}
