package sortingAlgorithm;

import java.util.Arrays;

/**
 * 基数排序算法(桶排序)
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 8, 78, 3, 21, 531, 13, 35, 1234, 134, 3142};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //找出 arr 数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //循环的最大轮数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length]; //一个二维数组充当十个桶
        int[] bucketCount = new int[10]; //保存每个桶存入的数据个数

        //根据各个位上数字进行排序，最终达成序列有序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 计算各个位数上的数字
                int dis = arr[j] / n % 10;
                bucket[dis][bucketCount[dis]] = arr[j];
                bucketCount[dis] += 1;
            }
            int index = 0;
            for (int k = 0; k < bucketCount.length; k++) {
                if (bucketCount[k] != 0) {
                    for (int g = 0; g < bucketCount[k]; g++) {
                        arr[index] = bucket[k][g];
                        index += 1;
                    }
                    bucketCount[k] = 0;
                }
            }

            System.out.println("第" + (i + 1) + "轮,根据" + n + "位排序");
        }
    }
}
