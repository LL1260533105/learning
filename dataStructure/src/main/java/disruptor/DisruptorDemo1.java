package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * disruptor 测试 ， 最为初始的demo
 */
public class DisruptorDemo1 {
    public static void main(String[] args) {
        // 环形队列 size 注意：环形数组只是逻辑层面的，物理结构是没有环形的
        int ringBufferSize = 1024;
        // 事件工厂
        LongEventFactory longEventFactory = new LongEventFactory();
        // 初始化 disruptor 设置三个参数 参数1：事件工厂， 参数2：环形数组大小， 参数3：线程产生工厂
        // 每一个消息发布后，消费者都是通过线程工厂产生一个线程去执行消费逻辑。
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, ringBufferSize, Executors.defaultThreadFactory());
        // 添加事件处理类
        disruptor.handleEventsWith(new LongEventHandle());
        // disruptor 运行起来
        disruptor.start();

        // 获取环形数组队列
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 获取可用的数组下标
        long sequence = ringBuffer.next();
        // 拿去数组中存放的 longEvent
        LongEvent longEvent = ringBuffer.get(sequence);
        // 设置改变值
        longEvent.setValue(111111);
        // 发布事件
        ringBuffer.publish(sequence);

        // 第二次发布事件
        long sequence2 = ringBuffer.next();
        LongEvent longEvent2 = ringBuffer.get(sequence2);
        longEvent2.setValue(111111);
        ringBuffer.publish(sequence2);
    }
}
