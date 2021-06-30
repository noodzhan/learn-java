package com.github.noodzhan.main.pattern.event;

/**
 * TODO
 *
 * @author noodzhan
 * @since 2021/6/2 7:03 下午
 */
public class MarkedListener {
    @CrudOpListener(OpEnum.INSERT)
    public void doStrongMethod(){
        System.out.println("注解标记的监听器");
    }
}
