package disruptor;

/**
 * disruptor 队列中的元素称之为事件 所以此处定义一个事件对象
 */
public class LongEvent{

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
