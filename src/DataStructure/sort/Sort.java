package DataStructure.sort;

import sun.security.krb5.internal.crypto.Aes128;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        //int[] array = {3, 9, -1, 9, 10, -2, -1};
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] array = new int[80000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (int) (Math.random() * 800000);
//        }
        //int[] array = {512,4,85,69,7,864,1};
        //int[] array = {1,0};
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();

        //bubbleSort(array);
        //selectSort(array);
        //insertSort(array);
        //shellSort(array);
        //quickSort(array,0,array.length-1);
//        temp = new int[array.length];
//        mergeSort(array, 0, array.length - 1);
        //radixSort(array);
        heapSort(array);

        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        //优化:如果在一趟排序时，没有发生一次交换，就说明已经有序，无需进行后续的排序，直接退出循环即可
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
            count++;
            if (!flag) {
                break; // 说明一趟排序没有交换
            } else {
                flag = false;
            }
        }
        System.out.println("共排序" + count + "趟");
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int changeindex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    changeindex = j;
                }
            }
            if (changeindex != i) {
                array[changeindex] = array[i];
                array[i] = min;
            }
        }
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertVal = array[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && array[insertIndex] > insertVal) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertVal;
        }
    }

    public static void shellSort(int[] array) {
        int group = array.length / 2;
        while (group != 0) {
            for (int i = group; i < array.length; i++) {
                int index = i - group;
                int value = array[i];
                while (index >= 0 && array[index] > value) {
                    array[index + group] = array[index];
                    index -= group;
                }
                array[index + group] = value;
            }
            group /= 2;
            //System.out.println(Arrays.toString(array));
        }
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) { //当相等时，说明只有一个元素，不需要排序
            return;
        }
        int k = part(array, start, end);
        quickSort(array, start, k - 1);
        quickSort(array, k + 1, end);
    }

    public static int part(int[] array, int start, int end) {
        int value = array[start];
        int i = start;
        int j = end + 1; // 因为在开头就--了，所以需要先加一
        while (true) {
            while (array[++i] < value) {
                if (i == end) break;
            }
            while (array[--j] > value) {
                if (j == start) break;
            }
            if (i >= j) {
                break;
            }
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        array[start] = array[j]; //重要j肯定指的是最后一个小于value的下标，i指的是第一个大于value的坐标
        array[j] = value;
        return j;
    }


    static int[] temp; // temp必须要声明成员变量，这样不会每次递归都生成temp对象。

    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = left + (right - left) / 2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, right, mid);//应该是原地归并
    }

    private static void merge(int[] array, int left, int right, int mid) {
        for (int i = left; i <= right; i++) { // 将temp看做是两个数组，left到mid，mid+1到right
            temp[i] = array[i];
        }
        int i = left; // array1的指针
        int j = mid + 1; // array2的指针
        for (int k = i; k <= right; k++) {
            if (i > mid) array[k] = temp[j++];
            else if (j > right) array[k] = temp[i++];
            else if (temp[i] <= temp[j]) array[k] = temp[i++];
            else array[k] = temp[j++];
        }
    }

    public static void radixSort(int[] array) {
        int[][] radix = new int[10][array.length];
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int len = (max + "").length();
        int[] index = new int[10];

        for (int k = 0, n = 1; k < len; k++, n *= 10) {
            int c = 0;
            for (int i = 0; i < array.length; i++) {
                int temp = ((array[i] / n) % 10);
                radix[temp][index[temp]++] = array[i];
            }
            for (int i = 0; i < 10; i++) {
                if (index[i] != 0) {
                    int t = index[i];
                    for (int j = 0; j < t; j++) {
                        array[c++] = radix[i][j];
                    }
                    index[i] = 0;
                }
            }
        }
        //System.out.println(Arrays.toString(array));
    }

    public static void heapSort(int[] arr){
        int N = arr.length;
        //这里从N/2开始，因为是完全二叉树，叶子节点无需下沉，
        for(int i = N/2; i>=0; i--){
            sink(arr, i, N);
        }//堆有序
        for(int i = N-1; i>0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sink(arr,0, i);
        }
    }

    /*

     */
    private static void sink(int[] arr, int i, int length){
        while(i * 2 + 1 < length){
            int j = i * 2 + 1;
            if(j+1<length && arr[j] < arr[j+1]){
                j++;
            }
            if(arr[i] < arr[j]){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }else{
                break;
            }
            i = j;
        }
    }
}
