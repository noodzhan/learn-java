package com.github.noodzhan.main.pattern.command;

/**
 * 命令的调用者
 *
 * @author noodzhan
 * @date 2021/4/29 11:47 上午
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }
    public void invokeCommand(){
        command.execute();
    }
}
