package DataStructure.sort;

import java.util.Scanner;

public class FindMaxK {
    public static void main(String[] args) {
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int [] pq = new int[k];
        for(int i=0;i<k;i++){
            pq[i] = arr[i];
        }
        initPQ(pq);
        for(int i = k; i < arr.length; i++){
            if(arr[i] > pq[0]){
                pq[0] = arr[i];
                sink(pq, 0, pq.length);
            }
        }

        for(int a : pq){
            System.out.println(a);
        }
    }

    private static void sink(int[] arr, int i, int length){
        while(i * 2 + 1 < length){
            int j = i * 2 + 1;
            if(j+1<length && arr[j] > arr[j+1]){
                j++;
            }
            if(arr[i] > arr[j]){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }else{
                break;
            }
            i = j;
        }
    }

    private static void initPQ(int[] pq){
        int N = pq.length;
        for(int i = N/2 ; i >= 0; i--){
            sink(pq, i, N);
        }
    }
}
