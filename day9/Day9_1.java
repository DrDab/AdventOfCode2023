package day9;

import java.util.*;
import java.io.*;

public class Day9_1 {

    public static long extrapolateNext(List<Long> deltas) {
        boolean allZeros = true;
        long last = deltas.get(0);

        List<Long> nextDeltas = new ArrayList<>();

        for (int i = 1; i < deltas.size(); i++) {
            long l = deltas.get(i);

            if (l != 0) {
                allZeros = false;
            }

            nextDeltas.add(l - last);
            last = l;
        }
        
        return allZeros ? last : last + extrapolateNext(nextDeltas);
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs/9.in"));
        
        long sum = 0L;
        
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            List<Long> deltas = new ArrayList<>();
            
            while (st.hasMoreTokens()) {
                deltas.add(Long.parseLong(st.nextToken()));
            }

            sum += extrapolateNext(deltas);
        }

        System.out.println(sum);
    }

}
