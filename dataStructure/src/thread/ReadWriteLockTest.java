package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 * 读锁 共享锁  可以同时又多个线程并行读
 * 写锁 排他锁  每次只能有一个线程进行写操作，并且当有线程正在读时，
 *             也是不能进行写操作的，等到所有的读或者写操作完成后，下一个写操作才能进行写操作
 */
public class ReadWriteLockTest {

    /**
     * 读写锁
     */
    public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    public Lock readLock = readWriteLock.readLock();

    /**
     * 写锁
     */
    public Lock writeLock = readWriteLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "  正在读");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "  正在写");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "  写完成");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                readWriteLockTest.read();
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                readWriteLockTest.write();
            }).start();
        }
    }
}
