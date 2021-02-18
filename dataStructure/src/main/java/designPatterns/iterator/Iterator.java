package designPatterns.iterator;

/**
 * 迭代器接口  通用的遍历集合的方法
 * @param <E>
 */
public interface Iterator<E> {
    /**
     * 判断有没有下一个元素
     * @return
     */
    boolean haveNext();

    /**
     * 获取下一个元素
     * @return
     */
    E next();
}