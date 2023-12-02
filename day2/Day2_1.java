package day2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day2_1 {
    private static final int RL = 12;
    private static final int GL = 13;
    private static final int BL = 14;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/2.in"));
        
        long sum = 0L;

        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            st.nextToken();

            int game = Integer.parseInt(st.nextToken().replaceAll(":", ""));
            int mr = 0;
            int mg = 0;
            int mb = 0;
            boolean isValid = true;

            while (isValid && st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                String color = st.nextToken();

                if (color.startsWith("blue")) {
                    mb = Math.max(num, mb);
                } else if (color.startsWith("red")) {
                    mr = Math.max(num, mr);
                } else {
                    mg = Math.max(num, mg);
                }

                isValid = mr <= RL && mb <= BL && mg <= GL;
            }

            if (isValid) {
                sum += game;
            }
        }

        System.out.println(sum);
    }
}
