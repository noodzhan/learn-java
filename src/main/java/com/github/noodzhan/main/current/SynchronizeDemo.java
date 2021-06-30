package com.github.noodzhan.main.current;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/20 5:56 下午
 */
public class SynchronizeDemo {
    private int count = 0;

    synchronized void increment() {
        this.count++;
    }

    void show() {
        System.out.println(this.count);
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizeDemo synchronizeDemo = new SynchronizeDemo();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                if (i == 1000) {
                    throw new IllegalArgumentException();
                }
                synchronizeDemo.increment();

            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronizeDemo.increment();
            }
        });

        thread1.start();
        Thread.sleep(500);
        thread2.start();

        thread1.join();
        thread2.join();

        synchronizeDemo.show();

    }
}
