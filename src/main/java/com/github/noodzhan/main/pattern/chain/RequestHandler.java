package com.github.noodzhan.main.pattern.chain;

/**
 * 请求处理接口
 *
 * @author noodzhan
 * @date 2021/4/29 11:12 上午
 */
@FunctionalInterface
public interface RequestHandler {
    void handle(Request request);
}
