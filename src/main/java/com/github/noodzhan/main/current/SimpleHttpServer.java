package com.github.noodzhan.main.current;

import java.net.Socket;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/1 4:41 下午
 */
public class SimpleHttpServer {
//    static ThreadPool<HttpRequestHandler> threadPool =

    static class HttpRequestHandler implements Runnable{
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }
}
