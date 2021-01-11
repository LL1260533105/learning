package thread;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    /**
     * 交换器  使用场景：两个线程之间交换某些信息
     */
    public static Exchanger exchanger = new Exchanger();
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            Object t = "0";
            System.out.println(Thread.currentThread().getName() + ":" + t);
            try {
                // 等待另外一个线程，进行数据交换
                t = exchanger.exchange(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + t);
        }).start();

        new Thread(() -> {
            Object t = "1";
            System.out.println(Thread.currentThread().getName() + ":" + t);
            try {
                t = exchanger.exchange(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + t);
        }).start();

//        Thread.sleep(2000);

        new Thread(() -> {
            Object t = "2";
            System.out.println(Thread.currentThread().getName() + ":" + t);
            try {
                // 等待另外一个线程，进行数据交换
                t = exchanger.exchange(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + t);
        }).start();

        new Thread(() -> {
            Object t = "3";
            System.out.println(Thread.currentThread().getName() + ":" + t);
            try {
                t = exchanger.exchange(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + t);
        }).start();
    }
}
