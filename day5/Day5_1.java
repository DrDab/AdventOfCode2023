package day5;

import java.util.*;
import java.io.*;

public class Day5_1 {
    private static class Range {
        public long sourceStart;
        public long sourceEnd;
        public long destStart;

        public Range(long sourceStart, long destStart, long maxDelta) {
            this.sourceStart = sourceStart;
            this.destStart = destStart;
            this.sourceEnd = sourceStart + maxDelta;
        }

        public long resolveDest(long source) {
            if (source > sourceEnd)
                throw new IllegalArgumentException("Out of range bounds!");
            return destStart + (source - sourceStart);
        }
    }

    private static final boolean DEBUG = false;
    private static final int NUM_MAPS = 7;

    private static Set<Long> seeds = new HashSet<>();

    @SuppressWarnings("unchecked")
    private static List<Range>[] maps = new ArrayList[NUM_MAPS];

    public static long explore(long seed) {
        long nextQuery = seed;

        for (int i = 0; i < NUM_MAPS; i++) {
            List<Range> ranges = maps[i];

            for (Range r : ranges) {
                if (nextQuery >= r.sourceStart && nextQuery <= r.sourceEnd) {
                    nextQuery = r.resolveDest(nextQuery);
                    break;
                }
            }
        }

        return nextQuery;
    }

    public static void main(String[] args) throws IOException {
        parse();

        long sol = Long.MAX_VALUE;

        for (Long seed : seeds) {
            sol = Math.min(sol, explore(seed));
        }

        System.out.println(sol);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/5.in"));
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        st.nextToken();

        while (st.hasMoreTokens()) {
            seeds.add(Long.parseLong(st.nextToken()));
        }

        sc.nextLine();

        for (int i = 0; i < NUM_MAPS; i++) {
            maps[i] = new ArrayList<Range>();

            sc.nextLine();
            String nextLine = sc.nextLine();

            while (nextLine.length() != 0) {
                st = new StringTokenizer(nextLine);

                long destStart = Long.parseLong(st.nextToken());
                long srcStart = Long.parseLong(st.nextToken());
                long maxDelta = Long.parseLong(st.nextToken());

                maps[i].add(new Range(srcStart, destStart, maxDelta));
                nextLine = sc.nextLine();
            }

            if (DEBUG)
                System.out.printf("Parsed %d ranges into map %d\n", maps[i].size(), i);
        }
    }
}
