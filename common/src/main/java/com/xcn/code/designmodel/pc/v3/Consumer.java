package com.xcn.code.designmodel.pc.v3;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Consumer implements Runnable {
    private List<String> dataList;
    private volatile boolean running = true;
    private String name = null;
    private AtomicLong al = new AtomicLong(0);

    public Consumer(List<String> blockingQueue, String name) {
        this.name = name;
        this.dataList = blockingQueue;
    }

    @Override
    public void run() {
        while (running) {
            Main.lock.lock();
            if (dataList.size() <= 0) {
                Main.condition.signalAll();
                try {
                    Main.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                String data = dataList.remove(0);
                System.out.println("Thread-" + name + " : " + data);
            }
            Main.lock.unlock();
        }
    }

    public void stop() {
        running = false;
    }
}
