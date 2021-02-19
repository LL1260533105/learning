package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * disruptor  消费者（观察者模式）
 * 消费生产者发布到队列当中的消息
 */
public class LongEventHandle implements EventHandler<LongEvent> {

    public int count = 0;
    /**
     *
     * @param longEvent 发布的消息数据
     * @param l 环形数组中的位置 sequence
     * @param b 是否是最后一个元素
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        count++;
        System.out.println( "当前线程：" + Thread.currentThread().getName() + "序号：" + l + "最终的值：" + longEvent.getValue());
    }
}
