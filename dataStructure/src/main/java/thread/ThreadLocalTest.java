package thread;

/**
 * ThreadLocal 测试
 */
public class ThreadLocalTest {

    /**
     * 初始化 threadLocal
     * 每个线程执行时，有自己独立的副本，并且相互之间不共享
     */
    public static ThreadLocal<Object> tl = new ThreadLocal();

    public void saveValue(Object obj){
        tl.set(obj);
    }
    public Object getValue(){
        return tl.get();
    }
    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        // 线程1，向 threadLocal中设置一个值， 这个值时线程1独有的，别的线程访问不到
       new Thread(() -> {
           threadLocalTest.saveValue(111);
       }).start();

       // 线程2 去拿 threadLocal 中的值，因其本身并没有向里面设置过值，所以取出的是 null
        new Thread(() -> {
            Object obj = threadLocalTest.getValue();
            System.out.println("取值：" +  obj);
        }).start();
    }
}
