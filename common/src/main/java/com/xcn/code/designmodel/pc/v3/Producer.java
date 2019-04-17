package com.xcn.code.designmodel.pc.v3;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Producer implements Runnable {

    private List<String> dataList;
    private volatile boolean running = true;
    private AtomicLong al;
    private String name;


    public Producer(List<String> blockingQueue, String name, AtomicLong al) {
        this.dataList = blockingQueue;
        this.al = al;
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {
            Main.lock.lock();
            if (dataList.size() > 10) {
                try {
                    Main.condition.signalAll();
                    Main.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                long v = al.getAndIncrement();
                dataList.add("" + v);
                System.out.println("Thread-" + name + " : " + v);
                try {
                    Thread.sleep(1000 * new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Main.lock.unlock();
        }
    }

    public void stop() {
        running = false;
    }
}
