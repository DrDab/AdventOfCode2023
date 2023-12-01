package day1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day1_2 {
    private static final String[] NUMS = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    private static int numValue(String s, int i) {
        if (s.charAt(i) >= '0' && s.charAt(i) <= '9') return s.charAt(i) - '0';

        for (int j = 0; j < NUMS.length; j++) {
            String numStr = NUMS[j];
            if (i + numStr.length() - 1 < s.length() && s.substring(i).startsWith(numStr)) {
                return j + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/1.in"));
        
        long sum = 0L;

        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            int l = 0;
            int r = s.length() - 1;

            int lv = -1;
            int rv = -1;

            while (lv == -1) {
                lv = numValue(s, l);
                l++;
            }

            while (rv == -1) {
                rv = numValue(s, r);
                r--;
            }

            sum += (lv * 10L) + rv;
        }

        System.out.println(sum);
    }
}
