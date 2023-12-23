package day10;

import java.util.*;
import java.io.*;

public class Day10_1 {
    public static int m = 0;
    public static int n = -1;
    public static List<String> map = new ArrayList<>();
    public static int startR;
    public static int startC;
    public static boolean[][] explored;

    public static long exploreLoop(int r, int c) {
        long curDist = 0;

        while (r != -1 && c != -1) {
            // Identify viable cells to explore based on current cell.
            // Check 4 directions for viability of exploration.
            boolean checkNorth = false;
            boolean checkSouth = false;
            boolean checkEast = false;
            boolean checkWest = false;
            char curCell = map.get(r).charAt(c);

            switch (curCell) {
                case 'S':
                    // If curCell is S, then check any VALID adjacent departing cell.
                    // No cell can be X, so X is the placeholder. Should never be X.
                    char neighbor = 'X';

                    if (r-1 >= 0) {
                        // Set checkNorth
                        neighbor = map.get(r-1).charAt(c);
                        if (neighbor == '|' || neighbor == 'F' || neighbor == '7')
                            checkNorth = true;
                    }

                    if (r+1 < m) {
                        // Set checkSouth
                        neighbor = map.get(r+1).charAt(c);
                        if (neighbor == '|' || neighbor == 'L' || neighbor == 'J')
                            checkSouth = true;
                    }

                    if (c-1 >= 0) {
                        // Set checkWest
                        neighbor = map.get(r).charAt(c-1);
                        if (neighbor == '-' || neighbor == 'L' || neighbor == 'F')
                            checkWest = true;
                    }

                    if (c+1 < n) {
                        // Set checkEast
                        neighbor = map.get(r).charAt(c+1);
                        if (neighbor == '-' || neighbor == 'J' || neighbor == '7')
                            checkEast = true;
                    }

                    if (neighbor == 'X')
                        throw new IllegalStateException("Didn't identify viable start");
                    break;

                case '|':
                    // If curCell is |, then only check vertical neighbors.
                    checkNorth = true;
                    checkSouth = true;
                    break;

                case '-':
                    // If curCell is -, then only check horizontal neighbors.
                    checkEast = true;
                    checkWest = true;
                    break;

                case 'L':
                    // Can go North or East.
                    checkNorth = true;
                    checkEast = true;
                    break;

                case 'J':
                    // Can go North or West.
                    checkNorth = true;
                    checkWest = true;
                    break;

                case '7':
                    // Can go South or West.
                    checkSouth = true;
                    checkWest = true;
                    break;

                case 'F':
                    // Can go South or East.
                    checkSouth = true;
                    checkEast = true;
                    break;

                // If curCell is . or something else, then we shouldn't get here.
                default:
                    throw new IllegalStateException("No periods!");
            }

            // Mark current cell as explored to prevent travelling back and forth, forever.
            explored[r][c] = true;
            curDist++;

            // Each cell can only have two neighbors.
            // We must have gotten to this cell through one of its two neighbors.
            // Hence, if one neighbor is unexplored, then that's the only valid one.
            // No need to travel elsewhere.
            if (checkNorth && r-1 >= 0 && !explored[r-1][c]) {
                r--;
            } else if (checkSouth && r+1 < m && !explored[r+1][c]) {
                r++;
            } else if (checkEast && c+1 < n && !explored[r][c+1]) {
                c++;
            } else if (checkWest && c-1 >= 0 && !explored[r][c-1]) {
                c--;
            } else {
                r = -1;
                c = -1;
            }
        }

        return curDist;
    }

    public static void main(String[] args) throws IOException {
        int[] startPos = parse();

        // Idea: Divide loop length by 2, floor.
        System.out.println(exploreLoop(startPos[0], startPos[1]) / 2L);
    }

    public static int[] parse() throws IOException {
        int[] res = new int[]{-1,-1};
        Scanner sc = new Scanner(new File("inputs/10.in"));

        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            if (n == -1)
                n = s.length();

            int startCol = s.indexOf('S');
            if (startCol != -1) {
                res[0] = m;
                res[1] = startCol;
            }

            map.add(s);
            m++;
        }

        explored = new boolean[m][n];
        return res;
    }
}
