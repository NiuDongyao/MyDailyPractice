package DataStructure.recursion;

public class Digui {
    public static void main(String[] args) {
//        System.out.println(mul(5));
        int[][] mg = new int[8][7];
        for(int i=0;i<7;i++){
            mg[0][i] = 1;
            mg[7][i] = 1;
        }
        for(int i=0; i<8;i++){
            mg[i][0] = 1;
            mg[i][6] = 1;
        }
        mg[3][1] = 1;
        mg[3][2] = 1;
        mg[2][2] = 1;
        setWay(mg,1,1);

        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(mg[i][j]+" ");
            }
            System.out.println();
        }
    }
    //迷宫问题,回溯
    //0表示未走过，1表示障碍，2表示通路，3表示死路
    //策略是下-右-上-左
    public static boolean setWay(int[][] a, int i, int j){
        if(a[6][5] == 2){
            return true;
        }
        if(a[i][j] == 0){
            a[i][j] = 2;
            if(setWay(a,i+1,j)){
                return true;
            }else if(setWay(a, i,j+1)){
                return true;
            }else if(setWay(a, i-1,j)){
                return true;
            }else if(setWay(a,i,j-1)){
                return true;
            }else{
                a[i][j] = 3;
                return false;
            }
        }else{
            return false;
        }
    }
    //阶乘
//    public static int mul(int n){
//        if(n == 0 || n == 1){
//            return 1;
//        }
//        return n*mul(n-1);
//    }


}
