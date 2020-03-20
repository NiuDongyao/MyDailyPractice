package DataStructure.search;

import java.util.*;

public class TestSearch {
    public static void main(String[] args) {
//        int[] arr = {1,2,5,8,12,1234,2000};
          int[] arr = {3,4,5,8,8,8,8,10,13,14};
//        List<Integer> list = binarySearchMany(arr, 66, 0, arr.length-1);
//        Collections.sort(list);
//        System.out.println(list);
          int index = binarySearch(arr, 8, 0, arr.length-1);
//        //int index = insertValueSearch(arr, 66, 0, arr.length - 1);
          System.out.println(index);
//        int index = fibSearch(arr, 1234);
//        System.out.println(index);

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int[] array = new int[n];
//        for(int i=0;i<n;i++){
//            array[i] = in.nextInt();
//        }
//        for(int i=0;i<m;i++){
//            int value = in.nextInt();
//            int index = fibSearch(array, value);
//            index++;
//            System.out.println(index);
//        }
    }

    private static int binarySearch(int[] arr, int target, int left, int right){
        System.out.println("二分");
        if(left > right || target > arr[right] || target < arr[left]){
            return -1;
        }
        int mid = (left + right) / 2;
        if(arr[mid] > target){
            return binarySearch(arr, target, left, mid-1);
        }else if(arr[mid] < target){
            return binarySearch(arr, target, mid+1, right);
        }else{
            int t = mid;
            while(arr[mid] == target && mid > 0){
                mid--;
            }
            if(t != mid){
                return ++mid;
            }
            return mid;

        }
    }

    private static int binarySearch02(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while(right >= left){
            mid = (left + right) / 2;
            if(nums[mid] == target){
                int t = mid;
                while(nums[mid] == target && mid > 0){
                    mid--;
                }
                if(t != mid){
                    return ++mid;
                }
                return mid;
            }
            if(nums[mid] > target){
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return -1;

    }

    private static ArrayList<Integer> binarySearchMany(int[] arr, int value, int left, int right){
        if(left > right || value > arr[right] || value < arr[left]){
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if(arr[mid] > value){
            return binarySearchMany(arr, value, left, mid -1);
        }else if(arr[mid] < value){
            return binarySearchMany(arr, value, mid + 1, right);
        }else {
            ArrayList<Integer> list = new ArrayList<>();

            int temp = mid - 1;
            while(temp != -1 && arr[temp] == arr[mid]){
                list.add(temp--);
            }

            list.add(mid);

            int temp01 = mid + 1;
            while(temp01 != arr.length && arr[temp01] == arr[mid]){
                list.add(temp01++);
            }
            return list;
        }
    }

    private static int insertValueSearch(int[] arr, int value, int left, int right){
        System.out.println("插值");
        if(left > right || value > arr[right] || value < arr[left]){
            return -1;
        }
        if(arr[left] == arr[right]){
            return left;
        }
        int mid = left + (value - arr[left]) / (arr[right] - arr[left]) * (right - left);
        if(arr[mid] > value){
            return insertValueSearch(arr, value, left, mid - 1);
        }else if(arr[mid] < value){
            return insertValueSearch(arr, value, mid + 1, right);
        }else{
            return mid;
        }
    }

    public static int[] fib(int k){//返回k个斐波那契数列
        int[] arr = new int[k+1];
        for(int i = 0; i<=k; i++){
            if(i < 2){
                arr[i] = 1;
                continue;
            }
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr;
    }

    private static int fibSearch(int[] arr, int value){ // fei bo na qi cha zhao
        int[] fib = fib(20);
        //System.out.println(Arrays.toString(fib));
        int left = 0;
        int k = 0;
        while(fib[k] - 1 < arr.length){
            k++;
        }
        // fib[k] = fib[k-1] + fib[k-2]
        // fib[k] - 1 = (fib[k-1] - 1) + (fib[k-2] - 1) + 1
        int[] temp = Arrays.copyOf(arr, fib[k]-1);
        // 把temp数组的长度扩充为和fib数列中的一个数再-1相同。
        // 然后后续使用temp数组按照fib划分进行查找。
        //System.out.println(Arrays.toString(temp));
        for(int j = arr.length - 1;j<fib[k]-2; j++){
            temp[j+1] = arr[arr.length - 1];
        }
        //System.out.println(Arrays.toString(temp));
        int right = temp.length - 1;

        while (left <= right){
            //把整个temp数组的长度看做fib[k]
            int mid = left + fib[k - 1] - 1;

            if(temp[mid] > value){
                right = mid - 1;
                k--;
            }else if(temp[mid] < value){
                left = mid + 1;
                k -= 2;
            }else{
                if(mid > arr.length - 1){
                    return arr.length - 1;
                }
                while(temp[mid--] == temp[mid]){
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }
}
