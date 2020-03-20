package DataStructure;

public class SparseArray {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[3][6] = 9;
        array[4][3] = 4;


        int i = 0;
        int j = 0;

        int[][] sparse = new int[4][3];

        int k = 1;
        for(; i<array.length;i++){
            for(j = 0; j < array[i].length;j++){
                if(array[i][j] != 0){
                    sparse[k][0] = i;
                    sparse[k][1] = j;
                    sparse[k++][2] = array[i][j];
                }
            }
        }
        System.out.println(i+""+j+" "+k);
        sparse[0][0] = i;
        sparse[0][1] = j;
        sparse[0][2] = k-1;

        for(int t=0; t<sparse.length;t++){
            for(j = 0;j<sparse[t].length;j++){
                System.out.print(sparse[t][j]+" ");
            }
            System.out.println();
        }
        int c = 1;
        int[][] rearray = new int[sparse[0][0]][sparse[0][1]];
        for(int a = 1; a<sparse.length; a++){
            rearray[sparse[a][0]][sparse[a][1]] = sparse[a][2];
        }

        for(int t=0; t<rearray.length;t++){
            for(j = 0;j<rearray[t].length;j++){
                System.out.print(rearray[t][j]+" ");
            }
            System.out.println();
        }
    }
}
