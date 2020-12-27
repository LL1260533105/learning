package designPatterns.observer;

/**
 * 观察者模式
 * 自我理解：当事件源对象发生一个动作（事件）时，观察者根据事件源对象的动作自动做出相应的反应
 */
public abstract class Observer {

    /**
     * 观察者观察到对应的事件时，执行相应的动作
     * @param event
     */
    public abstract void doAction(Event event);
}
