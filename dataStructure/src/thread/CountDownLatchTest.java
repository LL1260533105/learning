package thread;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch
 * 作用：使一个线程等待其他线程各自执行完毕后再执行
 */
public class CountDownLatchTest {

    public CountDownLatch countDownLatch = new CountDownLatch(3);

    public void countDownLatchTest(){
        try {
            System.out.println("countDownLatch 开始");
            // 等待其他线程执行完成
            countDownLatch.await();
            System.out.println("countDownLatch 结束");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void countDownLatch(){
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        // 线程执行到countDownLatchTest（） 方法中的 await 方法时，进入等待状态，直到 countDownLatch 的计数为0时，继续向下执行
        new Thread(()->{
            countDownLatchTest.countDownLatchTest();
        }).start();

        //
        for(int i = 0; i < 3; i++){
            new Thread(() -> {
                countDownLatchTest.countDownLatch();
                System.out.println("countDownLatch --1");
            }).start();
        }
    }
}
