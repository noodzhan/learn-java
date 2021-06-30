package com.github.noodzhan.main.current;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道实例
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/30 11:51 上午
 */
public class PipedDemo {
    public static void main(String[] args) throws IOException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        pipedWriter.connect(pipedReader);

        new Thread(new Print(pipedReader), "sout").start();

        int receive = 0;
        while ((receive = System.in.read()) != -1) {
            pipedWriter.write(receive);
            pipedWriter.flush();
        }

    }

    /**
     * 这个线程只负责打印
     */
    static class Print implements Runnable {
        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (IOException e) {

            }
        }
    }

}
