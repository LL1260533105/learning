package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 * 如下小程序 如果使用 ReentrantLock 加锁 ，main函数输出一定是start，然后end ，加上线程名一组一组的输出
 * 如果不加锁，两个线程输出则为start一起输出，然后输出end
 */
public class ReentrantLockTest {

    public ReentrantLock reentrantLock = new ReentrantLock();

    public void lockTest() {
        try {
            // 手动加锁
            reentrantLock.lock();
            System.out.println("test start:" + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 手动释放锁
            // 注意加锁后相应的业务执行完后，一定要释放锁
            reentrantLock.unlock();
            System.out.println("test end:" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        new Thread(() -> {
            test.lockTest();
        }).start();

        new Thread(() -> {
            test.lockTest();
        }).start();
    }
}
