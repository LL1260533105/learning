package designPatterns.singleton;

import java.util.Objects;

/**
 * 单利模式：双重检查
 */
public class SingleTonDoubleCheckLock {

    /**
     * volatile 作用：1.线程可见；
     *               2.禁止指令重排序
     */
    private static volatile SingleTonDoubleCheckLock singleTonDoubleCheckLock;

    private SingleTonDoubleCheckLock(){}

    public static SingleTonDoubleCheckLock getInstance(){
        if(Objects.isNull(singleTonDoubleCheckLock)){
            synchronized (SingleTonDoubleCheckLock.class){
                if(Objects.isNull(singleTonDoubleCheckLock)){
                    singleTonDoubleCheckLock = new SingleTonDoubleCheckLock();
                }
            }
        }
        return singleTonDoubleCheckLock;
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            SingleTonDoubleCheckLock singleTonDoubleCheckLock = SingleTonDoubleCheckLock.getInstance();
            System.out.println(singleTonDoubleCheckLock.hashCode());
        }
    }
}
