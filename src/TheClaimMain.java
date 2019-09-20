/*
 * 1) Create 2D array for grid, each line of file row
 * 2) Iterate through each character, stopping at 'A'
 * 3) For each A in that row, check if there's an 'A' at each
 *    point with corresponding coordinates of the two you already have
 * 4) For each of those potential points, test if there's an empty
 *    point that would complete the square
 * 5) Compute the size of that potential square
 * 6) Test if it's bigger than the one you already have
 * 7) If yes, overwrite, otherwise, continue algorithm
 * 8) Print final solution values
 *
 *
 * Ideas
 *  - Check if you can blacklist certain points already tested
 *    if would speed up algorithm
 *  - vars to store temporary points
 *  - only check to the right and down? prob not
 *    bc then if top corner is missing you screwed, but much faster.
 *          -> actually could be fine as long as after finding second
 *             you check both sides
 *          -> right now I'm saying only check below and right, then test
 *             for third point at like 4(?) other locations
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;

public class TheClaimMain {
    public static void main(String[] args) throws FileNotFoundException {
        int[] squareVals = new int[9];
        // STEP 1: Create 2D array for grid, each line of file row
        // specifies file to work with
        File inputFile = new File("input.txt");
        Scanner scnr = new Scanner(inputFile);
        // gets first line to determine array length
        String tempLine = scnr.nextLine();
        int arrayLength = tempLine.length();
        char[][] inputList = new char[arrayLength][arrayLength];
        // FIXME: Temporary redundancy since first line read to determine length and would be skipped in next loop
        for (int column = 0; column < arrayLength; column++) { inputList[0][column] = tempLine.charAt(column); }
        //iterates through each row (except first) and column to add each char to an array
        for (int row = 1; row < arrayLength; row++) {
            tempLine = scnr.nextLine();
            for (int column = 0; column < arrayLength; column++) {
                inputList[row][column] = tempLine.charAt(column);
            }
        }

        // STEP 2: Iterate through each character, stopping at 'A'
        for (int row = 0; row < inputList.length; row++) {
            for (int col = 0; col < inputList.length; col++) {
                checkForSquare(row, col, inputList, squareVals);
            }
        }
        for (int val : squareVals) {
            System.out.println(val);
        }
    }

    // STEP 3: For each A in that row, check if there's an 'A' at each
    //         point with corresponding coordinates of the two you already have
    private static void checkForSquare(int row, int col, char[][] list, int[] currentSquare) {
        int potentialSquareArea;
        for (int r = row; r < list.length; r++) {
            if (list[r][col] == 'A') {
                for (int c = 0; c < list.length; c++) {
                    if (list[r][c] == 'A') {
                        // STEP 4: For each of those potential points, test if there's an empty
                        // point that would complete the square
                        if (list[row][c] == '*') {
                            // calculate area based on distance between the sides of points
                            potentialSquareArea = (Math.abs(row - r) * Math.abs(col - c));
                            if (potentialSquareArea > currentSquare[8]) {
                                // FIXME: Hate doing this, but java can't replace all array vals at once?
                                currentSquare[0] = row;
                                currentSquare[1] = col;
                                currentSquare[2] = r;
                                currentSquare[3] = col;
                                currentSquare[4] = r;
                                currentSquare[5] = c;
                                currentSquare[6] = row;
                                currentSquare[7] = c;
                                currentSquare[8] = potentialSquareArea;
                            }
                        }
                    }
                }
            }
        }
        for (int c = col; c < list.length; c++) {
            if (list[row][c] == 'A') {
                for (int r = 0; r < list.length; r++) {
                    if (list[r][c] == 'A') {
                        // STEP 4: For each of those potential points, test if there's an empty
                        // point that would complete the square
                        if (list[r][col] == '*') {
                            // calculate area based on distance between sides of points
                            potentialSquareArea = (Math.abs(row - r) * Math.abs(col - c));
                            if (potentialSquareArea > currentSquare[8]) {
                                // FIXME: Hate doing this, but java can't replace all array vals at once?
                                currentSquare[0] = row;
                                currentSquare[1] = col;
                                currentSquare[2] = r;
                                currentSquare[3] = col;
                                currentSquare[4] = r;
                                currentSquare[5] = c;
                                currentSquare[6] = row;
                                currentSquare[7] = c;
                                currentSquare[8] = potentialSquareArea;
                            }
                        }
                    }
                }
            }
        }
    }
}
