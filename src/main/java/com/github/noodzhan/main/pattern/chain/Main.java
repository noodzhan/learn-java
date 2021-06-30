package com.github.noodzhan.main.pattern.chain;

import java.util.Objects;

/**
 * 测试待用主类
 *
 * @author noodzhan
 * @date 2021/4/29 11:19 上午
 */
public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        ConcreteHandler concreteHandler = new ConcreteHandler();
        concreteHandler.setNextHandler(new RequestHandler() {
            /**
             * 这里只是为了演示，可以在抽象出ConcreteHandler的类，包含下一级处理
             */
            private RequestHandler requestHandler = req -> System.out.println("处理下下节点");
            @Override
            public void handle(Request request) {
                System.out.println("处理下一节点");
                if (Objects.nonNull(requestHandler)) {
                    requestHandler.handle(request);
                }
            }

        });
        concreteHandler.handle(request);

    }
}
