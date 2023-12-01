package day1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day1_1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/1.in"));
        
        long sum = 0L;

        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            int l = 0;
            int r = s.length() - 1;

            while (s.charAt(l) < '0' || s.charAt(l) > '9') {
                l++;
            }

            while (s.charAt(r) < '0' || s.charAt(r) > '9') {
                r--;
            }

            sum += Long.parseLong(s.charAt(l) + "" + s.charAt(r));
        }

        System.out.println(sum);
    }
}