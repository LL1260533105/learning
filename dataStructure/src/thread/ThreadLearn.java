package thread;

/**
 * 多线程学习  可重入锁
 * 为什么 synchronized 必须要死可重入锁？
 * 以下面例子为例：如果 synchronized为非可重入锁，线程获取 function1 和 function2 都必须重新获取锁
 * 如果一个线程获取了 function1的锁，之后又要获取function2的锁是获取不到的，因为此时的锁的当前实例，
 * 锁只有一把，被获取了之后，其他的线程是获取不到的，这样就会造成死锁。所以 synchronized 必须为可重入锁
 */
public class ThreadLearn {

    /**
     * 同步方法1
     */
    public synchronized void function1() {
        System.out.println("同步方法1  " + Thread.currentThread().getName() + Thread.currentThread().getState());
    }

    /**
     * 同步方法
     */
    public synchronized void function2() {
        System.out.println("同步方法2  " + Thread.currentThread().getName() + Thread.currentThread().getState());
    }

    public static void main(String[] args) {
         new Thread(() -> {
            ThreadLearn threadLearn = new ThreadLearn();
            threadLearn.function1();
            threadLearn.function2();
        }).start();
        new Thread(() -> {
            ThreadLearn threadLearn = new ThreadLearn();
            threadLearn.function1();
            threadLearn.function2();
        }).start();
    }
}
