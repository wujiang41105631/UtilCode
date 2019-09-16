package com.xcn.code.designmodel.singleton;

/**
 *
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class SingletonDCL2 {

    public SingletonDCL2() {

    }

    private volatile transient static SingletonDCL2 ins = null;

    /**
     * DCL方式获取单例
     * ins如果不加volatile 多线程时可能会出问题.
     * 如果在构造方法中存在多条操作语句,线程A在new SingletonDCL2()时,可能会被挂起,此时构造方法可能只执行了一半.此时
     * 线程B在访问getInstance时间,ins不为null.会直接返回ins.使用volitile 会实现happens-before
     * happens-before原则定义如下:
     * 1. 如果一个操作happens-before(之前发生)另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。
     * 2. 两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的顺序来执行。如果重排序之后的执行结果与按照happens-before关系来执行的结果一致，那么这种重排序并不非法。
     * 和程序员相关的happens-before
     * 1. 同一个线程中，书写在前面的操作happen-before书写在后面的操作 [这条原则被称为单线程规则]。
     * 2. 对锁的unlock操作happen-before后续的对同一个锁的lock操作.这里的“后续”指的是时间上的先后关系，unlock操作发生在退出同步块之后，lock操作发生在进入同步块之前。这是条最关键性的规则，线程安全性主要依赖于这条规则。但是仅仅是这条规则仍然不起任何作用，它必须和下面这条规则联合起来使用才显得意义重大。这里关键条件是必须对“同一个锁”的lock和unlock.
     * 3. 如果操作A happen-before操作B，操作B happen-before操作C，那么操作A happen-before操作C。这条规则也称为传递规
     * 4. 对volatile字段的写操作happen-before后续的对同一个字段的读操作
     * 5. 线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作；
     * 6. 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生；
     * 7. 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行；
     * 8. 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始；
     * @return
     */
    public static SingletonDCL2 getInstance() {
        if (ins == null) {
            synchronized (SingletonDCL2.class) {
                if (ins == null) {
                    ins = new SingletonDCL2();
                }
            }
        }
        return ins;
    }
}
