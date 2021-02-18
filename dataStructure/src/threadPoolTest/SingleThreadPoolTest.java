package threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程线程池 测试
 * 为什么要单线程线程池？
 * 1.任务队列：保证任务的顺序执行
 * 2.线程的生命周期的管理
 */
public class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->{
            System.out.println("singleThreadExecutor test  :" + Thread.currentThread().getName());
        });

        executorService.execute(() ->{
            System.out.println("singleThreadExecutor test1 :"+ Thread.currentThread().getName());
        });
    }
}
