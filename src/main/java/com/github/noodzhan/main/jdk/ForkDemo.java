package com.github.noodzhan.main.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork测试使用demo
 * 这个其实本质就是利用了分治算法
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/12 10:22 上午
 */
public class ForkDemo {
    public static class CountTask extends RecursiveTask<Integer>{
        private static final int sand = 2;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //最小任务（递归结束条件）
            if (end - start < sand) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            }else{
                //划分任务
                int mid = (start + end) / 2;
                CountTask leftTask = new CountTask(start, mid);
                CountTask rightTask = new CountTask(mid + 1, end);
                leftTask.fork();//划分左侧任务
                rightTask.fork();//划分右侧任务
                Integer leftResult = leftTask.join();
                Integer rightResult = rightTask.join();
                sum = leftResult + rightResult;//合并结果
            }
            return sum;
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 10000);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);
        System.out.println(submit.get());

    }
}
