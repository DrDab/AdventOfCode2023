package day6;

import java.util.*;
import java.io.*;

public class Day6_1 {

    private static int n;
    private static List<Integer> times = new ArrayList<>();;
    private static List<Integer> distances = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        parse();

        int sol = 0;

        for (int race = 0; race < n; race++) {
            int total_time = times.get(race);
            int total_distance = distances.get(race);
            int num_ways = 0;

            for (int button_time = 1; button_time < total_time; button_time++) {
                if (button_time * (total_time - button_time) > total_distance) {
                    num_ways++;
                }
            }

            sol = sol == 0 ? num_ways : sol * num_ways;
        }

        System.out.println(sol);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/6.in"));
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        st.nextToken();

        while (st.hasMoreTokens()) {
            times.add(Integer.parseInt(st.nextToken()));
            n++;
        }

        st = new StringTokenizer(sc.nextLine());
        st.nextToken();

        while (st.hasMoreTokens()) {
            distances.add(Integer.parseInt(st.nextToken()));
        }
    }
}
