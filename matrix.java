import java.util.Arrays;

public class matrix {

    public static void main(String[] args) {
        int[][] mat1 = new int[][] {{1,2,3,4}, {5,6,7,8}};
        int[][] mat2 = new int[][] {{1,2,3,4}, {5,6,7,8}, {9,20,22,23}};
        int[][] squareMat = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};

        // print rows and cols; 
        printMatrix(mat1);
        printMatrix(mat2);

        printTranspose(squareMat);
        printTranspose(mat1);
        printTranspose(mat2);


    }
    private static void printMatrix(int[][] mat) {
        for(int i = 0 ; i < mat.length; i ++ ) {
            System.out.println(Arrays.toString(mat[i]));
        }

        for(int j = 0 ; j < mat[0].length; j++) {
            int[] col = new int[mat.length];
            for(int i = 0 ; i < mat.length; i++) {
                col[i] = mat[i][j];
            }

            System.out.println(Arrays.toString(col));
        }
    }
    private static void printTranspose(int[][] mat) {
        System.out.println("Before transpose ");
        for(int[] r : mat) {
            System.out.println(Arrays.toString(r));
        }

        int[][] transpose = new int[mat[0].length][mat.length];
        for(int i = 0; i< mat.length; i++) {
            for(int j = 0 ; j < mat[0].length; j++) {
                transpose[j][i] = mat[i][j];
            }
        }
        System.out.println("After transpose ");

        for(int[] r : transpose) {
            System.out.println(Arrays.toString(r));
        }
        
    }        
        /*
         * For square only
         * for(int i = 0 ; i < mat.length; i++) {
         * for(int j = i ; j < mat[0].length; j++) {
         * int temp = mat[i][j];
         * mat[i][j] = mat[j][i];
         * mat[j][i] = temp;
         * }
         * }
         */

        
    
}
