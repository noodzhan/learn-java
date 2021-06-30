package com.github.noodzhan.main.current;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 线程管理的示例
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/22 10:52 下午
 */
public class ManagementFactoryDemo {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo threadInfo : threadMXBean.dumpAllThreads(false, false)) {
            System.out.println(threadInfo.getThreadName());
        }
    }
}
/**
 * 结果
 * main
 * Reference Handler
 * Finalizer
 * Signal Dispatcher
 * Common-Cleaner
 * Monitor Ctrl-Break
 */
