package day8;

import java.util.*;
import java.io.*;

public class Day8_1 {
    public static String directions;
    public static Map<String, String> leftAdj = new HashMap<>();
    public static Map<String, String> rightAdj = new HashMap<>();

    public static void main(String[] args) throws IOException {
        parse();

        String currentNode = "AAA";
        int step = 0;

        while (!currentNode.equals("ZZZ")) {
            char nextMove = directions.charAt(step % directions.length());
            currentNode = nextMove == 'L' ? leftAdj.get(currentNode) : rightAdj.get(currentNode);
            step++;
        }

        System.out.println(step);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/8.in"));
        directions = sc.nextLine();
        sc.nextLine();

        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String node = st.nextToken();
            st.nextToken();
            String left = st.nextToken().substring(1, 4);
            String right = st.nextToken().substring(0, 3);
            leftAdj.put(node, left);
            rightAdj.put(node, right);
        }
    }
}
