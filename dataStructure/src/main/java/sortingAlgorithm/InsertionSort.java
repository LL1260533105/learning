package sortingAlgorithm;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 8, 78, 3, 21, 531, 13, 35, 1234, 134, 3142};
        insertionSort(arr);
        shellSort(arr);
        shellSort2(arr);
        printf(arr);
    }

    /**
     * 插入排序算法
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        // 一个数默认是有序的，所以从第二数开始进行排序 即 i  初始化为 1 ;
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            // 按照从大到小的顺序排列
            while (insertIndex >= 0 && insertValue > arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
        }
    }

    /**
     * 希尔排序算法(交换法)
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp;
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arr.length; i++) {
                for (int j = i - grap; j >= 0; j -= grap) {
                    if (arr[j] > arr[j + grap]) {
                        temp = arr[j];
                        arr[j] = arr[j + grap];
                        arr[j + grap] = temp;
                    }
                }
            }
        }
    }


    /**
     * 希尔排序算法   移位法
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int index = i;
                int indexValue = arr[i];
                if (arr[index] > arr[index - gap]) {
                    while (index - gap >= 0 && indexValue > arr[index - gap]) {
                        arr[index] = arr[index - gap];
                        index -= gap;
                    }
                    arr[index] = indexValue;
                }
            }
        }
    }


    public static void printf(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }


}
