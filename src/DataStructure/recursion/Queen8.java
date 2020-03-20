package DataStructure.recursion;/* 八皇后问题回溯暴力求解 */

public class Queen8 {
    static int printCount = 0;
    static int max = 8;
    int[] array = new int[max];/*使用一维数组模拟八皇后问题，数组的下标代表在第几行，对应的值代表在第几列*/

    public static void main(String[] args) {
        Queen8 q = new Queen8();
        q.check(0);
        System.out.println("共" + printCount + "种摆法");
    }

    void check(int n) {
        if (n == max) { /*n==8说明前8个皇后都已经摆放好了*/
            printCount++;
            for (int i = 0; i < array.length; i++) System.out.print((array[i] + 1) + " ");
            System.out.println();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;   /* 把第n+1个皇后摆放到第i个位置*/
            if (judge(n)) {   /* 检测第n+1个皇后和前n个皇后有无冲突,无冲突返回true,开始摆放下一个*/
                check(n + 1);
            }
        }
    }

    boolean judge(int n) { /* 判断第n+1个皇后和前n个皇后有无冲突*/
        for (int i = 0; i < n; i++)
            if (array[i] == array[n] || Math.abs(i - n) == Math.abs((array[i] - array[n]))) return false;
        return true;
    }
}
