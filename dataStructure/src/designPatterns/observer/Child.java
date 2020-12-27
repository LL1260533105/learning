package designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Child implements Source {

    /**
     * 观察者集合
     */
    private static List<Observer> observerList;

    /**
     * 初始化观察者集合
     */
    static{
        observerList = new ArrayList<>();
        observerList.add(new DadObserver());
    }

    /**
     * 小孩动作 哭
     */
    public void cry (){
        Event<Child> event = new Event<>(this);

        /**
         * 当观察者观察到事件源对象发出对应的动作时，观察者做出相应的反应
         */
        observerList.forEach(observer -> {
            observer.doAction(event);
        });
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.cry();
    }
}
