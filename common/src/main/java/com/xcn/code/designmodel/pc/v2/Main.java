package com.xcn.code.designmodel.pc.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 生产者消费者模式,采用缓冲队列采用list,通过synchronized 中的wait和notifyAll做到异步通知.
 *
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ec = Executors.newCachedThreadPool();
        List<String> bq = new ArrayList<>(10);
        AtomicLong al = new AtomicLong(0);
        Producer p1 = new Producer(bq, al);
        Producer p2 = new Producer(bq, al);
        Producer p3 = new Producer(bq, al);
        Consumer c1 = new Consumer(bq, "consumer1");
        Consumer c2 = new Consumer(bq, "consumer2");
        ec.execute(p1);
        ec.execute(p2);
        ec.execute(p3);
        ec.execute(c2);
        ec.execute(c1);
        Thread.sleep(100000);
        p1.stop();
        p2.stop();
        p3.stop();
        c1.stop();
        c2.stop();
        System.out.println(al.get());
        Thread.sleep(3000);
        ec.shutdown();
    }
}
