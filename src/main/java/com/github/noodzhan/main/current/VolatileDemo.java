package com.github.noodzhan.main.current;

/**
 * 测试内存的可见性
 * volatile
 *
 * @author noodzhan
 * @since 2021/6/19 4:40 下午
 */
public class VolatileDemo {

    private volatile int count = 0;

//    private int count = 0;

//    private AtomicInteger count = new AtomicInteger(0);

    public void increment(){
        this.count++;
    }
    public void decline(){
        this.count--;
    }

//    public void increment(){
//        this.count.incrementAndGet();
//    }
//    public void decline(){
//        this.count.decrementAndGet();
//    }
    public void show(){
        System.out.println(this.count);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                volatileDemo.increment();
            }
        });


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                volatileDemo.increment();
            }
        });

        thread.start();
        thread1.start();


        thread.join();
        thread1.join();

        volatileDemo.show();


    }

}
