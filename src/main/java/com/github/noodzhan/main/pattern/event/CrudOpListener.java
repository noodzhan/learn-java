package com.github.noodzhan.main.pattern.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作监听器
 *
 * @author noodzhan
 * @since 2021/6/2 6:41 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CrudOpListener {
    OpEnum value();
}
