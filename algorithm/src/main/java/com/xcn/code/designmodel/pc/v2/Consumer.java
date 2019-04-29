package com.xcn.code.designmodel.pc.v2;

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
            synchronized (dataList){
                if(dataList.size() <= 0){
                    dataList.notifyAll();
                    try {
                        dataList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            String data = dataList.remove(0);
            System.out.println("Thread-" + name + " : " + data);
        }
    }

    public void stop() {
        running = false;
    }
}
