package designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 * 代理通过代理接口实现，意味着使用jdk动态代理的类 必须有实现接口，这个是jdk自身的api决定的
 */
public class JdkAutoProxy {

    public static void main(String[] args) {
        Tank tank = new Tank();
        // 保存动态生存出来的代理对象的class文件
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        // 通过调用 Proxy 的 newProxyInstance方法动态代理出一个movable对象
        // 解析：
        // param1 classLoader 一般使用被代理对象的classLoader, 注意可以是父类的类加载器，但是不能是平行级别的类加载器
        // param2 接口数组  jdk动态代理基于接口进行代理的，这里主要是指定代理那些接口
        // param3 InvocationHandle 被代理对象方法执行时的处理器，比如添加日志等等一系列操作都在这个处理器中执行，这里采用的匿名内部类的方式实现的
        //        也可以自己实现一个InvocationHandler接口的类来进行处理 例如下方 myInvocationHandle 实现类
        Movable move = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(), new Class[]{Movable.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法开始啦。。。。");
                Object obj = method.invoke(tank, args);
                System.out.println("方法结束啦。。。。");
                return obj;
            }
        });
        move.move();
    }
}

interface Movable {
    void move();
}

class Tank implements Movable {

    @Override
    public void move() {
        System.out.println("moving  kalalala....");
    }
}

class myInvocationHandle implements InvocationHandler {

    private Tank tank;

    public myInvocationHandle(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法开始啦。。。。");
        Object obj = method.invoke(tank, args);
        System.out.println("方法结束啦。。。。");
        return obj;
    }
}