package com.xcn.code.designmodel.pc.v1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 生产者消费者模式,方法1,采用jdk自带的 LinkedBlockingQueue实现数据的共享,不用考虑notify,wait问题.
 *
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ec = Executors.newCachedThreadPool();
        LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<String>(10);
        AtomicLong al = new AtomicLong(0);
        Producer p1 = new Producer(bq, al);
        Consumer c1 = new Consumer(bq, "consumer");
        ec.execute(p1);
        ec.execute(c1);
        Thread.sleep(100000);
        p1.stop();
        c1.stop();

        Thread.sleep(3000);
        ec.shutdown();
    }
}
