package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 事件 longEvent 的生产工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
