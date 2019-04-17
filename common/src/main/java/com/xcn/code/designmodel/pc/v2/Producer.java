package com.xcn.code.designmodel.pc.v2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Producer implements Runnable {

    private List<String> dataList;
    private volatile boolean running = true;
    private AtomicLong al;

    public Producer(List<String> blockingQueue, AtomicLong al) {
        this.dataList = blockingQueue;
        this.al = al;
    }

    @Override
    public void run() {
        while(running){
            synchronized (dataList){
                if(dataList.size()>10){
                    dataList.notifyAll();
                    try {
                        dataList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    dataList.add("" + al.getAndIncrement());
                    try {
                        Thread.sleep(1000 * new Random().nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void stop() {
        running = false;
    }
}
