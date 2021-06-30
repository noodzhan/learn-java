package com.github.noodzhan.main.pattern.chain;

import java.util.Objects;

/**
 * 具体请求处理者
 *
 * @author noodzhan
 * @date 2021/4/29 11:14 上午
 */
public class ConcreteHandler implements RequestHandler{
    private RequestHandler requestHandler;
    @Override
    public void handle(Request request) {
        System.out.println("FirstHandler处理请求");
        if (Objects.nonNull(requestHandler)) {
            requestHandler.handle(request);
        }
    }
    public void setNextHandler(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }
}
