package designPatterns.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *  * 代理模式：代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用
 * cglib 动态代理  实现方式：生成被代理对象的子类
 * 对于 final 修饰的类 是不能够被代理的
 */
public class CglibAutoProxy {

    public static void main(String[] args) {
        // 增强
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(Tank2.class);
        // 设置处理逻辑 类似jdk动态代理的InvocationHandler
        enhancer.setCallback(new MyMethodIntercept());
        // 生成代理对象
        Tank2 tank2 = (Tank2) enhancer.create();
        tank2.move();
    }
}

// 被代理对象执行时  需要织入的逻辑
class MyMethodIntercept implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        String name = o.getClass().getSuperclass().getName();
        System.out.println("方法开始啦。。。。");
        Object obj = method.invoke(o, objects);
        System.out.println("方法结束啦。。。。");
        return obj;
    }
}
// 被代理对象
class Tank2 {

    public void move() {
        System.out.println("moving  kalalala....");
    }
}