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

public class TheClaimMain {
    public static void main(String[] args) throws FileNotFoundException {
        // STEP 1: Create 2D array for grid, each line of file row
        // specifies file to work with
        File inputFile = new File("input.txt");
        Scanner scnr = new Scanner(inputFile);
        // gets first line to determine array length
        String tempLine = scnr.nextLine();
        int arrayLength = tempLine.length();
        char[][] inputList = new char[arrayLength][arrayLength];
        //FIXME: Temporary redundancy since first line read to determine length and would be skipped in next loop
        for (int column = 0; column < arrayLength; column++) { inputList[0][column] = tempLine.charAt(column); }
        //iterates through each row (except first) and column to add each char to an array
        for (int row = 1; row < arrayLength; row++) {
            tempLine = scnr.nextLine();
            for (int column = 0; column < arrayLength; column++) {
                inputList[row][column] = tempLine.charAt(column);
            }
        }

        // STEP 2: Iterate through each character, stopping at 'A'
        for (char[] chArray : inputList) {
            for (char ch : chArray) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }
    // testing updating gist
}
