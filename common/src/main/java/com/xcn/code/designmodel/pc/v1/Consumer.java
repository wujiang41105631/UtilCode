package com.xcn.code.designmodel.pc.v1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Consumer implements Runnable {
    private LinkedBlockingQueue<String> blockingQueue;
    private volatile boolean running = true;
    private String name = null;
    private AtomicLong al = new AtomicLong(0);

    public Consumer(LinkedBlockingQueue<String> blockingQueue, String name) {
        this.name = name;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String data = blockingQueue.take();
                System.out.println("Thread-" + name + " : " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop() {
        running = false;
    }
}
