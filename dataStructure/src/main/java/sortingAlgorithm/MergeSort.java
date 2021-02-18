package sortingAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 归并算法
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        int[] temp = new int[arr.length];
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        mergeSort(arr, 0, arr.length - 1, temp);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分  将问题细小化
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左递归
            mergeSort(arr, left, mid, temp);
            //右递归
            mergeSort(arr, mid + 1, right, temp);

            merge(arr, left, mid, right, temp);
        }
    }


    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, t = 0;
        // 将两个有序得序列 按照顺序进行合并 直到其中一个序列处理完了
        while (i <= mid && j <= right) {
            if (arr[i] >= arr[j]) {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            } else {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }
        }

        //将剩余数据依次填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将 temp 中得数据复制给 arr
        t = 0;
        int leftTemp = left;
        while (leftTemp < right) {
            arr[leftTemp] = temp[t];
            leftTemp += 1;
            t += 1;
        }

    }
}
