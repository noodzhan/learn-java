package com.github.noodzhan.main.pattern.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 操作接口
 *
 * @author noodzhan
 * @since 2021/6/2 5:38 下午
 */
public class Operate {
    private List<OperateListener> listeners = new ArrayList<>();
    public void operate(){
        System.out.println("operating");
        OperateEvent event = new OperateEvent("operate", 1);
        notifyAllListener(event);
    }
    public void addListener(OperateListener listener){
        listeners.add(listener);
    }

    public void addListener(OperateListener... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    public void addListener(List<OperateListener> listeners) {
        this.listeners.addAll(listeners);
    }
    private void notifyAllListener(OperateEvent operateEvent){
        for (OperateListener listener : listeners) {
            listener.doOperateCallback(operateEvent);
        }
        return;
    }

    public static void main(String[] args) {
        Operate operate = new Operate();
        operate.addListener(event -> {
            if (event.getSource().equals("operate")) {
                System.out.println("常规注册的监听器");
            }
        });


        CrudOpRegister crudOpRegister = new CrudOpRegister(operate);
        crudOpRegister.register(MarkedListener.class);


        operate.operate();

    }
}
