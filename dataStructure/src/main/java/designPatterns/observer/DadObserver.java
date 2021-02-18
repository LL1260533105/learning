package designPatterns.observer;

public class DadObserver extends Observer {

    public void doAction(Event event) {
        System.out.println("抱抱宝宝" + "\n");
        System.out.println(event.getSource());
    }
}
