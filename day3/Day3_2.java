package day3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3_2 {
    private static int m = 0;
    private static int n = 0;
    private static char[][] map = new char[200][200];
    private static boolean[][] visited = new boolean[200][200];

    private static int[][] dirs = new int[][] { { -1, -1 },
            { -1, 0 },
            { -1, 1 },
            { 0, -1 },
            { 0, 1 },
            { 1, -1 },
            { 1, 0 },
            { 1, 1 } };

    // Return the corresponding number if map[r][c] is the cell of an undiscovered
    // number, -1 otherwise.
    private static long inspect(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || map[r][c] < '0' || map[r][c] > '9' || visited[r][c])
            return -1L;

        int lowest = c;
        int highest = c;

        for (int nc = c; nc < n && map[r][nc] >= '0' && map[r][nc] <= '9'; nc++) {
            visited[r][nc] = true;
            highest = nc;
        }

        for (int nc = c - 1; nc >= 0 && map[r][nc] >= '0' && map[r][nc] <= '9'; nc--) {
            visited[r][nc] = true;
            lowest = nc;
        }

        long res = 0L;
        long mag = 1L;

        for (int nc = highest; nc >= lowest; nc--) {
            res += (map[r][nc] - '0') * mag;
            mag *= 10L;
        }

        return res;
    }

    private static void clearVisited(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || !visited[r][c])
            return;

        for (int nc = c; nc < n && visited[r][nc]; nc++) {
            visited[r][nc] = false;
        }

        for (int nc = c - 1; nc >= 0 && visited[r][nc]; nc--) {
            visited[r][nc] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        parse();

        long sum = 0L;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '*') {
                    // We are on a gear.
                    // First get the number of adjacent numbers.
                    int adj = 0;
                    long product = 1L;

                    for (int[] dir : dirs) {
                        long val = inspect(i - dir[0], j - dir[1]);

                        if (val != -1L) {
                            product *= val;
                            adj++;
                        }

                        if (adj > 2)
                            break;
                    }

                    // Unmark the visited cells.
                    for (int[] dir : dirs) {
                        clearVisited(i - dir[0], j - dir[1]);
                    }

                    // If there are exactly two adjacent numbers, then add the product.
                    if (adj == 2) {
                        sum += product;
                    }
                }
            }
        }

        System.out.println(sum);
    }

    private static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/3.in"));

        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            if (n == 0)
                n = s.length();

            for (int i = 0; i < n; i++) {
                map[m][i] = s.charAt(i);
            }

            m++;
        }
    }
}
