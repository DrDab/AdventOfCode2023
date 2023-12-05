package day5;

import java.util.*;
import java.io.*;

public class Day5_2 {
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

    private static List<Long> seedStarts = new ArrayList<>();
    private static List<Long> seedLens = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static List<Range>[] maps = new ArrayList[NUM_MAPS];

    public static void explore(Set<Long> res, long seed, int mapId) {
        List<Range> ranges = maps[mapId];

        boolean wingIt = true;

        for (Range r : ranges) {
            if (seed >= r.sourceStart && seed <= r.sourceEnd) {
                long nextQuery = r.resolveDest(seed);

                if (mapId+1 < NUM_MAPS) {
                    explore(res, nextQuery, mapId+1);
                } else {
                    res.add(nextQuery);
                }
                
                wingIt = false;
            }
        }

        if (wingIt) {
            if (mapId+1 < NUM_MAPS) {
                explore(res, seed, mapId+1);
            } else {
                res.add(seed);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        parse();

        long min = Long.MAX_VALUE;

        for (int i = 0; i < seedStarts.size(); i++) {
            long seedStart = seedStarts.get(i);
            long seedEnd = seedStart + seedLens.get(i) - 1L;

            System.out.println(i + ") " + seedStart + " " + seedEnd);

            for (long seed = seedStart; seed <= seedEnd; seed++) {
                Set<Long> hs = new HashSet<>();
                explore(hs, seed, 0);
                for (Long l : hs) {
                    min = Math.min(l, min);
                }
            }
        }

        System.out.println(min);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/5.in"));
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        st.nextToken();

        while (st.hasMoreTokens()) {
            seedStarts.add(Long.parseLong(st.nextToken()));
            seedLens.add(Long.parseLong(st.nextToken()));
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
