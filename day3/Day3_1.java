package day3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3_1 {
    private static int m = 0;
    private static int n = 0;
    private static char[][] map = new char[200][200];
    private static boolean[][] explored = new boolean[200][200];

    private static int[][] dirs = new int[][] { { 0, 1 },
            { 1, 0 },
            { 0, -1 },
            { -1, 0 },
            { 1, 1 },
            { -1, -1 },
            { -1, 1 },
            { 1, -1 } };

    private static boolean inspect(int r, int c) {
        // Check out of bounds or non numeric.
        if (r < 0 || r >= m || c < 0 || c >= n || map[r][c] < '0' || map[r][c] > '9' || explored[r][c])
            return false;

        // In bounds. Mark explored.
        explored[r][c] = true;

        // Probe 8 adjacent cells for engine part.
        boolean success = false;

        for (int[] dir : dirs) {
            int nr = r - dir[0];
            int nc = c - dir[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
            if ((map[nr][nc] < '0' || map[nr][nc] > '9') && map[nr][nc] != '.') success = true;
        }

        // non-terminating OR to force exploration.
        return success | inspect(r, c - 1);
    }

    public static void main(String[] args) throws IOException {
        parse();

        long sum = 0L;

        for (int i = 0; i < m; i++) {
            for (int j = n-1; j >= 0; j--) {
                if (inspect(i, j)) {
                    long mag = 1L;

                    for (int k = j; k >= 0 && map[i][k] >= '0' && map[i][k] <= '9'; k--) {
                        sum += mag * (map[i][k] - '0');                        
                        mag *= 10L;
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
