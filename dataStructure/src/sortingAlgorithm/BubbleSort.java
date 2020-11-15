package sortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 冒泡算法
 * 算法逻辑是通过两次嵌套循环进行数据大小的比较
 * 所以时间复杂度是 O(n^2)
 * 随着数据量的增大 耗时越多
 */
public class BubbleSort<E extends Comparable> {

    public List<E> bubbleSort(List<E> list) {

        for (int i = 0; i < list.size(); i++) {
            boolean isComplete = true;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    isComplete = false;
                    E temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
            // 优化 如果判断一圈下来 没有进行数据的位置交换，说明排序已经完成，不需要进行后续的执行，提升算法效率
            if(isComplete){
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Num> list = new ArrayList();
        list.add(new Num(5));
        list.add(new Num(1));
        list.add(new Num(22));
        list.add(new Num(0));
        list.add(new Num(6));
        list.add(new Num(3));
        System.out.println(list);
        System.out.println("\n");
        BubbleSort<Num> bubbleSort = new BubbleSort();
        List<Num> nums = bubbleSort.bubbleSort(list);
        System.out.println(nums);
    }
}

/**
 * 算法实验类
 */
class Num implements Comparable<Num> {
    public int num;

    public Num(int num) {
        this.num = num;
    }

    @Override
    public int compareTo(Num o) {
        return this.num > o.num ? 1 : -1;
    }
}
