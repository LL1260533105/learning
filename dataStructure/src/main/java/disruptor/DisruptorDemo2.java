package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * disruptor 测试 ， 最为初始的demo
 */
public class DisruptorDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 环形队列 size
        int ringBufferSize = 1024;
        // 事件工厂
        LongEventFactory longEventFactory = new LongEventFactory();
        // 初始化 disruptor 设置三个参数 参数1：事件工厂， 参数2：环形数组大小， 参数3：线程产生工厂, 参数4：ProducerType 生产类型，参数5：队列满时的等待策略
        // ProducerType 生产类型 默认是 MULTI类型的，这种类型表示在进行添加和获取环形数组里面的元素都是加锁的，
        //                             SINGLE类型是无锁操作，明确只有一个线程操作disruptor时，使用这种类型。
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, ringBufferSize, Executors.defaultThreadFactory(),ProducerType.SINGLE, new BlockingWaitStrategy());
        LongEventHandle longEventHandle = new LongEventHandle();
        // 添加事件处理类
        disruptor.handleEventsWith(longEventHandle);

        // 消费者异常时，disruptor 不能就停止，需要继续向下执行，需要如下操作设置异常处理逻辑 实现 ExceptionHandler
        disruptor.handleExceptionsFor(longEventHandle).with(new ExceptionHandler(){

            @Override
            public void handleEventException(Throwable throwable, long l, Object o) {

            }

            @Override
            public void handleOnStartException(Throwable throwable) {

            }

            @Override
            public void handleOnShutdownException(Throwable throwable) {

            }
        });
        // disruptor 运行起来
        disruptor.start();

        // 获取环形数组队列
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
/*        // 获取可用的数组下标
        long sequence = ringBuffer.next();
        // 拿去数组中存放的 longEvent
        LongEvent longEvent = ringBuffer.get(sequence);
        // 设置改变值
        longEvent.setValue(111112);
        // 发布事件
        ringBuffer.publish(sequence);

        // 第二次发布事件
        long sequence2 = ringBuffer.next();
        LongEvent longEvent2 = ringBuffer.get(sequence2);
        longEvent2.setValue(111151);
        ringBuffer.publish(sequence2);*/

        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i< 50; i++){
            final long threadNum = i;
            executor.submit(() ->{
                for(int j = 0; j < 100; j++){
                    ringBuffer.publishEvent((event,sequence3,l) -> event.setValue(l), threadNum);
                }
            });
        }
        executor.shutdown();
        //disruptor.shutdown();
        Thread.sleep(5000);
        System.out.println(longEventHandle.count);
    }
}
