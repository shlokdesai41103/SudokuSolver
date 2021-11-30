/**
 * Shlok Desai
 * Created On: May 2nd, 2021
 * Class for solving sudoku grids.
 */

import java.util.Scanner;
import java.util.ArrayList;
  
public class SudokuSolver{
    public static int[][] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Create Grid
        arr = new int[9][9];
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                arr[i][j] = in.nextInt();
            }
        }

        //Array used to test code, entering the whole grid can be very tiresome
        /*int[][] arr = 
                {{5, 8, 0, 2, 0, 0, 4, 7, 0},
                {0, 2, 0, 0, 0, 0, 0, 3, 0},
                {0, 3, 0, 0, 5, 4, 0, 0, 0},
                {0, 0, 0, 5, 6, 0, 0, 0, 0},
                {0, 0, 7, 0, 3, 0, 9, 0, 0},
                {0, 0, 0, 0, 9, 1, 0, 0, 0},
                {0, 0, 0, 8, 2, 0, 0, 6, 0},
                {0, 7, 0, 0, 0, 0, 0, 8, 0},
                {0, 9, 4, 0, 0, 6, 0, 1, 5}};*/

        //Solving the grid
        boolean solvable = solve(arr);

        System.out.println();

        //Final Conclusion
        if(solvable == true){
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 9 ; j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }
        else if(solvable == false){
            System.out.println("This puzzle cannot be solved.");
        }

    }
    
    /**
     * 
     * @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
     * @return: returns a boolean value that decides if the grid has been solved or if a certain value will lead to a successful solve of the puzzle.
     */
    public static boolean solve(int[][] arr){

        int[] unassignedPos = findUnassigned(arr);
        if(unassignedPos[0] == -1){//this indicates that the sudoku grid has been completed as there are no empty coordinates left.
            return true;
        }

        int i = unassignedPos[0];//row number
        int j = unassignedPos[1];//column number
                    
        for(int n = 1 ; n < 10 ; n++){//testing numbers between 1-9 at the unassigned location discovered.
            if(possible(arr, i, j, n)){
                arr[i][j] = n;
                boolean isitSolvable = solve(arr);
                if(isitSolvable == true){
                    return true;
                }
                arr[i][j] = 0;
            }
            
        }
                    
        return false;

    }

    /**
     * 
     * @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
     * @return an array of two values that indicate the row number and the column number of the unassigned position.
     */
    public static int[] findUnassigned(int[][] arr){

        int[] unassigned = new int[2];
        unassigned[0] = -1;
        unassigned[1] = -1;

        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){

                if(arr[i][j] == 0){
                    unassigned[0] = i;
                    unassigned[1] = j;
                    return unassigned;
                }

            }
        }

        return unassigned;

    }

    /**
     * 
     * @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
     * @param row: the row number we are checking.
     * @param col: the column number in the grid that we are checking.
     * @param n: the number whose possibility is being checked in the method.
     * @return: this will return a boolean value that will indicate if the number n can be placed in the position.
     */
    public static boolean possible(int[][] arr, int row, int col, int n){
        //checking the whole row
        for(int i = 0 ; i < 9 ; i++){
            if(arr[row][i] == n){
                return false;
            }
        }

        //checking the whole column
        for(int i = 0 ; i < 9 ; i++){
            if(arr[i][col] == n){
                return false;
            }
        }

        //checking the whole square
        int row0 = (row/3)*3;
        int col0 = (col/3)*3;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(arr[row0+i][col0+j] == n){
                    return false;
                }
            }
        }

        return true;

    }

}