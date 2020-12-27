package designPatterns.observer;

/**
 * 事件
 */
public class Event<T> {
    /**
     * 事件源
     * 某些情况下 观察者的动作执行需要使用到事件源对象，所以这里将事件源对象当做一个属性存放起来
     */
    T source;


    public Event(T source){
        this.source = source;
    }

    /**
     * 获取事件源对象
     */
    T getSource(){
        return source;
    }
}
