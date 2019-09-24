import java.io.*;
import java.util.Scanner;

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
        // redundancy since first line read to determine length and would be skipped in next loop, iterates first column
        for (int column = 0; column < arrayLength; column++) { inputList[0][column] = tempLine.charAt(column); }
        // iterates through each row (except first) and column to add each char to an array
        for (int row = 1; row < arrayLength; row++) {
            tempLine = scnr.nextLine();
            for (int column = 0; column < arrayLength; column++) {
                inputList[row][column] = tempLine.charAt(column);
            }
        }

        // STEP 2: Iterate through each character, stopping at 'A'
        for (int row = 0; row < inputList.length; row++) {
            for (int col = 0; col < inputList.length; col++) {
                if (inputList[row][col] == 'A') {
                    checkForSquare(row, col, inputList, squareVals);
                }
            }
        }
        // writes to output file
        try {
            PrintWriter writer = new PrintWriter("output.txt");
            for (int i = 0; i < squareVals.length - 1; i++) {
                writer.println(squareVals[i] + 1);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // STEP 3: For each A in that row, check if there's an 'A' at each
    //         point with corresponding coordinates of the two you already have
    private static void checkForSquare(int row, int col, char[][] list, int[] currentSquare) {
        int potentialSquareArea;
        for (int r = row; r < list.length; r++) {
            if (list[r][col] == 'A') {
                int sideLength = r - row;
                // test for col + sideLength (downwards)
                if (col + sideLength < list.length && list[r][col + sideLength] == 'A') {
                    // STEP 4: For each of those potential points, test if there's an empty
                    // point that would complete the square
                    if (list[row][col + sideLength] == '*') {
                        // calculate area based on distance between sides of points
                        potentialSquareArea = (sideLength * sideLength);
                        if (potentialSquareArea > currentSquare[8]) {
                            // Hate doing this, but java can't replace all array vals at once from what I've seen
                            currentSquare[0] = row;
                            currentSquare[1] = col;
                            currentSquare[2] = r;
                            currentSquare[3] = col;
                            currentSquare[4] = r;
                            currentSquare[5] = col + sideLength;
                            currentSquare[6] = row;
                            currentSquare[7] = col + sideLength;
                            currentSquare[8] = potentialSquareArea;
                        }
                    }
                }
                // test for col - sideLength (upwards)
                if (col - sideLength >= 0 && list[r][col - sideLength] == 'A') {
                    // STEP 4: For each of those potential points, test if there's an empty
                    // point that would complete the square
                    if (list[row][col - sideLength] == '*') {
                        // calculate area based on distance between sides of points
                        potentialSquareArea = (sideLength * sideLength);
                        if (potentialSquareArea > currentSquare[8]) {
                            currentSquare[0] = row;
                            currentSquare[1] = col;
                            currentSquare[2] = r;
                            currentSquare[3] = col;
                            currentSquare[4] = r;
                            currentSquare[5] = col - sideLength;
                            currentSquare[6] = row;
                            currentSquare[7] = col - sideLength;
                            currentSquare[8] = potentialSquareArea;
                        }
                    }
                }
            }
        }
        for (int c = col; c < list.length; c++) {
            if (list[row][c] == 'A') {
                int sideLength = c - col;
                // test for row + sideLength (towards right)
                if (row + sideLength < list.length && list[row + sideLength][c] == 'A') {
                    // STEP 4: For each of those potential points, test if there's an empty
                    // point that would complete the square
                    if (list[row + sideLength][col] == '*') {
                        // calculate area based on distance between sides of points
                        potentialSquareArea = (sideLength * sideLength);
                        if (potentialSquareArea > currentSquare[8]) {
                            currentSquare[0] = row;
                            currentSquare[1] = col;
                            currentSquare[2] = row + sideLength;
                            currentSquare[3] = col;
                            currentSquare[4] = row + sideLength;
                            currentSquare[5] = c;
                            currentSquare[6] = row;
                            currentSquare[7] = c;
                            currentSquare[8] = potentialSquareArea;
                        }
                    }
                }
                // test for row - sideLength (towards left)
                if (row - sideLength >= 0 && list[row - sideLength][c] == 'A') {
                    // STEP 4: For each of those potential points, test if there's an empty
                    // point that would complete the square
                    if (list[row - sideLength][col] == '*') {
                        // calculate area based on distance between sides of points
                        potentialSquareArea = (sideLength * sideLength);
                        if (potentialSquareArea > currentSquare[8]) {
                            currentSquare[0] = row;
                            currentSquare[1] = col;
                            currentSquare[2] = row - sideLength;
                            currentSquare[3] = col;
                            currentSquare[4] = row - sideLength;
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
