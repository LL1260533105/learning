package sortingAlgorithm;


/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 8, 78, 3, 21, 531, 13, 35, 1234, 134, 3142};
        selectSort(arr);
        printf(arr);
    }

    /**
     * 选择排序算法
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
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
