package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * LockSupport 测试
 * <p>
 * LockSupport 是 current 下面的一个工具类，提供了一堆static方法，比如park(),unpark()等；
 * LockSupport 可以灵活的控制线程，唤醒指定的线程，暂定当前线程等一系列的操作。
 * <p>
 * 题目：定义一个容器 包含add添加元素 和 getCount 获取元素数量的方法
 * 启动一个线程添加10个元素，另外启动一个线程监控容器元素个数，当添加了五个元素时结束监控
 */
public class LockSupportTest {

    private List<Object> list = new ArrayList<>();

    /**
     * 添加元素
     *
     * @param i
     */
    public void add(int i) {
        list.add(i);
    }

    /**
     * 获取元素数量
     *
     * @return
     */
    public int getCount() {
        return list.size();
    }

    static Thread thread2;
    static Thread thread1;

    public static void main(String[] args) {

        LockSupportTest lockSupportTest = new LockSupportTest();
        /*
         *//**
         * 添加元素线程
         *//*
        thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockSupportTest.add(i);
                System.out.println("add:" + i);
                if (lockSupportTest.getCount() == 5) {
                    // 唤醒 thread1 线程，让其继续执行
                    LockSupport.unpark(thread1);
                    // 本线程暂停执行
                    LockSupport.park();
                }
            }
        });

        *//**
         * 监控线程  监控容器元素个数，到五个就结束监控
         *//*
        thread1 = new Thread(() -> {
            // 当前线程暂停执行，等待被唤醒
            LockSupport.park();
            System.out.println("到5个啦");
            // 唤醒 thread2 线程，让其继续执行
            LockSupport.unpark(thread2);
        });
        thread1.start();
        thread2.start();*/

        lockSupportTest.second();
    }

    /**
     * 面试题第二种实现方式
     */

    public void second() {
        LockSupportTest lockSupportTest = new LockSupportTest();
        // 通过线程的 wait 和 唤醒 达到效果
        //注意：两个线程必须使用同一把锁
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("数量为5个啦");
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if (lockSupportTest.getCount() == 5) {
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("添加第" + i + "个");
                    lockSupportTest.add(i);
                }
            }
        }).start();
    }
}
