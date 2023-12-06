package day5;

import java.util.*;
import java.io.*;

public class Day5_2 {
    private static class Range {
        public long sourceStart;
        public long destStart;
        public long destEnd;

        public Range(long sourceStart, long destStart, long maxDelta) {
            this.sourceStart = sourceStart;
            this.destStart = destStart;
            this.destEnd = destStart + maxDelta;
        }

        public long resolveSrc(long dest) {
            if (dest > destEnd)
                throw new IllegalArgumentException("Out of range bounds!");
            return sourceStart + (dest - destStart);
        }
    }

    private static final boolean DEBUG = false;
    private static final int NUM_MAPS = 7;

    private static List<Long> seedStarts = new ArrayList<>();
    private static List<Long> seedLens = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static List<Range>[] maps = new ArrayList[NUM_MAPS];

    public static void exploreBack(Set<Long> res, long dest, int mapId) {
        List<Range> ranges = maps[mapId];

        boolean wingIt = true;

        for (Range r : ranges) {
            if (dest >= r.destStart && dest <= r.destEnd) {
                long nextQuery = r.resolveSrc(dest);

                if (mapId - 1 >= 0) {
                    exploreBack(res, nextQuery, mapId - 1);
                } else {
                    res.add(nextQuery);
                }

                wingIt = false;
            }
        }

        if (wingIt) {
            if (mapId - 1 >= 0) {
                exploreBack(res, dest, mapId - 1);
            } else {
                res.add(dest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        parse();
        Set<Long> possibleSeeds = new HashSet<>();

        for (long dest = 0L;; dest++) {
            exploreBack(possibleSeeds, dest, NUM_MAPS - 1);

            for (Long seed : possibleSeeds) {
                for (int i = 0; i < seedStarts.size(); i++) {
                    long seedStart = seedStarts.get(i);
                    long seedEnd = seedStart + seedLens.get(i) - 1L;
                    if (seed >= seedStart && seed <= seedEnd) {
                        System.out.println(dest);
                        return;
                    }
                }  
            }

            possibleSeeds.clear();
        }
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
