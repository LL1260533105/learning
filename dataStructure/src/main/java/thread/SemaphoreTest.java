package thread;

import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 * 作用：限流
 */
public class SemaphoreTest {

    /**
     * 信号量 初始化了5个信号，表示同一时刻最多只有5个线程同时执行
     */
    public static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 获取信号，获得了信号才可以继续向下执行，否在就等待，直到其他线程释放信号，并且获得信号后继续执行
                    // 另外线程释放信号后，当前线程不一定立马就能够获得信号
                    semaphore.acquire();
                    System.out.println("线程：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放信号
                    semaphore.release();
                }

            }).start();
        }
    }
}
