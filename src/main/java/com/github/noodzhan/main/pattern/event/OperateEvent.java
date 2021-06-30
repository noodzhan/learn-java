package com.github.noodzhan.main.pattern.event;

import java.util.EventObject;

/**
 * 操作事件
 *
 * @author noodzhan
 * @since 2021/6/2 5:32 下午
 */
public class OperateEvent extends EventObject {
    private Integer kind;

    public OperateEvent(Object source, Integer kind) {
        super(source);
        this.kind = kind;
    }
}
