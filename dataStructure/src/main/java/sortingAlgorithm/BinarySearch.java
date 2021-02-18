package sortingAlgorithm;

/**
 * 二分查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {

    }

    /**
     * 使用二分查找的前提是 序列必须是有序的
     *
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (midValue > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else if (midValue < value) {
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    /**
     * 插值查找算法
     * 适用于数据量大 并且连续性强，分布比较均匀的有序序列
     *
     * @param arr       需查找序列
     * @param left      左边下标
     * @param right     右边下标
     * @param findValue 需被查询数据
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //arr[0] > findValue 和 arr[arr.length - 1] < findValue 这两个条件必须要有， 不然 mid 出现可能越界异常
        if (left > right || arr[0] > findValue || arr[arr.length - 1] < findValue) {
            return -1;
        }
        // 自适应的中间值   中间值算法固定为下面计算方式
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (midValue > findValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else if (midValue < findValue) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }
}
