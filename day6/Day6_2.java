package day6;

import java.util.*;
import java.io.*;

public class Day6_2 {
    private static long total_time = 0L;
    private static long total_distance = 0L;

    public static void main(String[] args) throws IOException {
        parse();

        long sol = 0L;

        for (int button_time = 1; button_time < total_time; button_time++) {
            if (button_time * (total_time - button_time) > total_distance) {
                sol++;
            }
        }

        System.out.println(sol);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/6.in"));

        String timeStr = sc.nextLine();
        String distStr = sc.nextLine();

        long time_mag = 1L;
        long distance_mag = 1L;

        for (int i = timeStr.length() - 1; i >= 0; i--) {
            char c = timeStr.charAt(i);
            if (c >= '0' && c <= '9') {
                total_time += (time_mag * (c - '0'));
                time_mag *= 10L;
            }
        }

        for (int i = distStr.length() - 1; i >= 0; i--) {
            char c = distStr.charAt(i);
            if (c >= '0' && c <= '9') {
                total_distance += (distance_mag * (c - '0'));
                distance_mag *= 10L;
            }
        }
    }
}
