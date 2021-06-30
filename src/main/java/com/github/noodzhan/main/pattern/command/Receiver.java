package com.github.noodzhan.main.pattern.command;

/**
 * 命令的接受者
 *
 * @author noodzhan
 * @date 2021/4/29 11:48 上午
 */
public abstract class Receiver {
    private Command command;

    public Receiver(Command command) {
        this.command = command;
    }
    public abstract void action();
}
