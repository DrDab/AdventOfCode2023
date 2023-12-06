package day6;

import java.util.*;
import java.io.*;

public class Day6_2 {
    private static long total_time = 0L;
    private static long total_distance = 0L;

    public static void main(String[] args) throws IOException {
        parse();

        long sol = 0L;
        int button_time = 1;

        // The integer directly preceding midpoint of 1,2,...,total_time-1 if
        // total_time-1 is odd, is (total_time - 1) / 2.
        for (; button_time <= (total_time - 1) / 2; button_time++) {
            if (button_time * (total_time - button_time) > total_distance) {
                sol += 2L;
            }
        }

        // Now, button_time is on the midpoint time iff total_time-1 is odd.
        // Otherwise, we've exceeded the (nonexistent) midpoint time.
        // If total_time-1 is odd, then there exists a midpoint time which has no
        // corresponding alternate sol. Check if that time beats the race.
        if ((total_time - 1) % 2 != 0 &&
                button_time * (total_time - button_time) > total_distance) {
            sol++;
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
