package com.github.noodzhan.main.pattern.command;

/**
 * 测试主类
 *
 * @author noodzhan
 * @date 2021/4/29 11:51 上午
 */
public class Main {
    public static void main(String[] args) {
        Command command = () -> {
            System.out.println("命令");
        };

    }
}
