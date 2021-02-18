package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    /**
     * 栏栅，使用场景：等待多个线程某一时刻或者节点一起执行
     *      并且cyclicBarrier是循环的，可以重复多次计数使用
     */
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                // 等待其他线程执行，直到cyclicBarrier的计数为0时，多个线程继续执行
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName());
        }).start();

        Thread.sleep(10000);

        new Thread(() -> {
            try {
                // 等待其他线程执行，直到cyclicBarrier的计数为0时，多个线程继续执行
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName());
        }).start();
    }
}
