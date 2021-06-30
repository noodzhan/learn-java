package com.github.noodzhan.main.pattern.command;

/**
 * 具体命令，必须含有一个接口者，命令至少要发送一个接受者
 *
 * @author noodzhan
 * @date 2021/4/29 11:56 上午
 */
public class ConcreteCommand implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
