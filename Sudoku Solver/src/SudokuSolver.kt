/**
* Shlok Desai
* Created On: May 3rd, 2021
* This program has been created to solve sudoku grids
* */

import java.util.Scanner

fun main(){

    val reader = Scanner(System.`in`)

    //Create Grid
    val arr = Array(9) {IntArray(9)}
    for (i in 0..8){
        for (j in 0..8){
            arr[i][j] = reader.nextInt()
        }
    }



    for(i in 0..8){
        for(j in 0..8){
            val num = arr[i][j]
            print("$num ")
        }
        println()
    }

    //Solve Grid
    val solvable: Boolean = solve(arr)

    println()

    //Final Conclusion
    if(solvable){
        for(i in 0..8){
            for(j in 0..8){
                val num = arr[i][j]
                print("$num ")
            }
            println()
        }
    }
    else if(!solvable){
        print("This puzzle cannot be solved.")
    }
}

/**
* @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
* @return a boolean value that decides if the grid has been solved or if a certain value will lead to a successful solve of the puzzle.
* */
fun solve(arr: kotlin.Array<IntArray>): Boolean{

    val unassignedPos = findUnassigned(arr)
    if (unassignedPos[0] == -1){//this indicates that the sudoku grid has been completed as there are no empty coordinates left.
        return true
    }

    val i: Int = unassignedPos[0]//row number
    val j: Int =  unassignedPos[1]//column number

    for (n in 1..9){//testing numbers between 1-9 at the unassigned location discovered.

        if (possible(arr, i, j, n)){
            arr[i][j] = n
            val isitSolvable: Boolean = solve(arr)
            if(isitSolvable){
                return true
            }
            arr[i][j] = 0
        }

    }
    return false
}

/**
 *
 * @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
 * @return an array of two values that indicate the row number and the column number of the unassigned position.
 */
fun findUnassigned(arr: Array<IntArray>): Array<Int>{
    val unassigned = arrayOf(-1, -1)
    for(i in 0..8){
        for(j in 0..8) {

            if (arr[i][j] == 0) {
                unassigned[0] = i
                unassigned[1] = j
                return unassigned
            }

        }
    }

    return unassigned

}

/**
 *
 * @param arr: 2d array that contains numbers from 1-9 and represents the sudoku grid.
 * @param row: the row number we are checking.
 * @param col: the column number in the grid that we are checking.
 * @param n: the number whose possibility is being checked in the method.
 * @return: this will return a boolean value that will indicate if the number n can be placed in the position.
 */
fun possible(arr: Array<IntArray>, row: Int,col: Int, n: Int): Boolean{

    //checking the whole row
    for(i in 0..8){
        if(arr[row][i] == n){
            return false
        }
    }

    //checking the whole colum
    for (i in 0..8){
        if(arr[i][col] == n){
            return false
        }
    }

    //checking the whole square
    val row0: Int = (row/3)*3
    val col0: Int = (col/3)*3
    for (i in 0..2){
        for (j in 0..2){
            if(arr[row0+i][col0+i] == n){
                return false
            }
        }
    }

    return true
}



