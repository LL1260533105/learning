package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * disruptor  消费者（观察者模式）
 */
public class LongEventHandle implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue() + "当前线程：" + Thread.currentThread().getName() + "序号：" + l);
    }
}
