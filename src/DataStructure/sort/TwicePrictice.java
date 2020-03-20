package DataStructure.sort;

import sun.security.krb5.internal.crypto.Aes128;

import java.util.Arrays;

public class TwicePrictice {
    public static void main(String[] args) {
        //int[] array = {3, 9, -1, 9, 10, -2, -1};
        //int[] array = {1,2,5,6,3,4,8,9};

        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 800000);
        }
        //System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();

        //quickSort(array, 0, array.length-1);
        temp = new int[array.length];
        //merge(array, 0, array.length-1,(array.length-1)/2);
        mergeSort(array, 0, array.length-1);

        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int left, int right) {
        if(left >= right){
            return;
        }
        int k = partition(array, left, right);
        quickSort(array, left, k-1);
        quickSort(array, k+1, right);
    }
    private static int partition(int[] array,int left, int right){
        int value = array[left];
        int i = left;
        int j = right+1;
        while(i<j){
            while (array[++i] < value){
                if(i == right) break;
            }
            while (array[--j] > value){
                if(j == left) break;
            }
            if(i>=j){
                break;
            }else{
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        if(j != left){
            array[left] = array[j];
            array[j] = value;
            System.out.println(Arrays.toString(array));
        }
        return j;
    }
    public static int[] temp;
    private static void mergeSort(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, right, mid);
    }
    private static void merge(int[] array, int left, int right, int mid){
        int i = left;
        int j = mid +1;
        for(int t=left;t<right+1;t++){
            temp[t] = array[t];
        }
        for(int t=left;t<right+1;t++){
            if(i > mid){
                array[t] = temp[j++];
            }else if(j > right){
                array[t] = temp[i++];
            }else if(temp[i] <= temp[j]){
                array[t] = temp[i++];
            }else{
                array[t] = temp[j++];
            }
        }
    }
}
