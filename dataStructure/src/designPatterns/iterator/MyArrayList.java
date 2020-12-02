package designPatterns.iterator;

/**
 * 迭代器模式 主要应用于集合的遍历
 */
public class MyArrayList<E> {
    /**
     *  元素
     */
    private Object[] elements;
    /**
     * 数组大小
     */
    private int size = 0;

    /**
     * 默认创建长度为10的数组
     */
    public MyArrayList() {
        this.elements = new Object[10];
    }

    /**
     * 添加元素
     */
    public void add(E e) {
        // 当数组满了时 对数组进行扩容 扩大原本长度的一半
        if (elements.length == this.size) {
            int length = elements.length;
            Object[] newElements = new Object[length + length / 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        elements[size] = e;
        size++;
    }

    /**
     * 获取集合长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回集合自己的迭代器（不同类型的集合，实现自己不同的迭代器逻辑）
     *
     * @return
     */
    public Itr iterator() {
        return new Itr();
    }

    /**
     * 私有内部类 实现迭代器接口
     */
    private class Itr implements Iterator<E> {

        private int currentSize = 0;

        @Override
        public boolean haveNext() {
            if (this.currentSize < size) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            E e = (E) elements[currentSize];
            currentSize++;
            return e;
        }
    }
}

class test {
    public static void main(String[] args) {
        MyArrayList<Object> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 12; i++) {
            myArrayList.add(i);
        }

        Iterator iterator = myArrayList.iterator();
        while (iterator.haveNext()) {
            System.out.println(iterator.next() + "\n");
        }
    }
}