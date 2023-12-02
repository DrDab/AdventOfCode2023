package day2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day2_2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/2.in"));
        
        long sum = 0L;

        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            st.nextToken();
            st.nextToken();
            int mr = 0;
            int mg = 0;
            int mb = 0;

            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                String color = st.nextToken();

                if (color.startsWith("blue")) {
                    mb = Math.max(num, mb);
                } else if (color.startsWith("red")) {
                    mr = Math.max(num, mr);
                } else {
                    mg = Math.max(num, mg);
                }
            }

            sum += (mr * mg * mb);
        }

        System.out.println(sum);
    }
}
