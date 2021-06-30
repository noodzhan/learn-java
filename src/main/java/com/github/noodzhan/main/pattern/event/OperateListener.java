package com.github.noodzhan.main.pattern.event;

import java.util.EventListener;

/**
 * 操作监听者
 *
 * @author noodzhan
 * @since 2021/6/2 5:35 下午
 */
@FunctionalInterface
public interface OperateListener extends EventListener {
    void doOperateCallback(OperateEvent event);
}
