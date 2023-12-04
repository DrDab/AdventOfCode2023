package day4;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Day4_1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/4.in"));

        long totalPoints = 0L;

        Set<Integer> winningNums = new HashSet<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            st.nextToken();

            String nextToken = st.nextToken();

            while (!nextToken.equals("|")) {
                winningNums.add(Integer.parseInt(nextToken));
                nextToken = st.nextToken();
            }

            long tmpSum = 0L;

            while (st.hasMoreTokens()) {
                int toCheck = Integer.parseInt(st.nextToken());
                if (winningNums.contains(toCheck)) {
                    tmpSum = tmpSum == 0L ? 1L : tmpSum * 2L;
                }
            }

            totalPoints += tmpSum;
            winningNums.clear();
        }

        System.out.println(totalPoints);
    }
}
