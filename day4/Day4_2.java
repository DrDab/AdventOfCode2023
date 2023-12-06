package day4;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Day4_2 {
    private static Map<Integer, Long> cardToMatchMap = new HashMap<>();
    private static Map<Integer, Long> memo = new HashMap<>();

    // Let DP(i) be the number of additional cards that card i awards.
    // Then, DP(i) = matches(i) + DP(i+1) + DP(i+2) + ... + DP(i+matches(i)).

    private static long calculate(int cardNum) {
        if (!cardToMatchMap.keySet().contains(cardNum)) {
            throw new IllegalArgumentException("Doesn't exist " + cardNum);
        }

        if (memo.containsKey(cardNum)) return memo.get(cardNum);

        long base = cardToMatchMap.get(cardNum);
        long recursive = 0L;

        for (long i = 1L; i <= base; i++) {
            recursive += calculate((int)(cardNum + i));
        }

        long res = base + recursive;
        memo.put(cardNum, res);
        return res;
    }

    public static void main(String[] args) throws IOException {
        parse();

        // Sum the number of initial cards...
        long sol = cardToMatchMap.size();

        // Then add the number of recursively won cards for each card.
        for (Integer cardNum : cardToMatchMap.keySet()) {
            sol += calculate(cardNum);
        }

        System.out.println(sol);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/4.in"));
        int i = 1;

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
                    tmpSum++;
                }
            }

            cardToMatchMap.put(i, tmpSum);

            winningNums.clear();
            i++;
        }
    }
}
