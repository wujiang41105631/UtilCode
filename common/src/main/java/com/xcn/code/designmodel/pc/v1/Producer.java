package com.xcn.code.designmodel.pc.v1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Producer implements Runnable {

    private LinkedBlockingQueue<String> blockingQueue;
    private volatile boolean running = true;
    private AtomicLong al;

    public Producer(LinkedBlockingQueue<String> blockingQueue, AtomicLong al) {
        this.blockingQueue = blockingQueue;
        this.al = al;
    }

    @Override
    public void run() {
        while (running) {
            try {
                blockingQueue.put("" + al.getAndIncrement());
                Thread.sleep(1000 * new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
