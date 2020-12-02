package designPatterns.proxy;

/**
 * 代理模式：代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用
 * 静态代理
 */
public class StaticProxy {
    public static void main(String[] args) {
        Tank1 tank = new Tank1();
        // 添加代理 有点类似装饰器模式 一层套一层
        LogProxy logProxy = new LogProxy(tank);
        logProxy.move();
    }
}

/**
 * 接口
 */
interface Movable1{
    void move();
}

/**
 * 坦克类  被代理对象
 */
class Tank1 implements Movable1{
    @Override
    public void move() {
        System.out.println("moving kalakala......");
    }
}

/**
 * 日志代理类
 */
class LogProxy implements Movable1{

    private Movable1 movable1;

    public LogProxy(Movable1 movable1){
        this.movable1 = movable1;
    }

    /**
     * 被代理对象执行时  在方法的前后添加日志
     */
    @Override
    public void move() {
        System.out.println("方法开始啦。。。。");
        movable1.move();
        System.out.println("方法结束啦。。。。");
    }
}