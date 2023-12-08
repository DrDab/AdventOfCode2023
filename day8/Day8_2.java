package day8;

import java.util.*;
import java.io.*;

public class Day8_2 {
    public static String directions;
    public static Map<String, String> leftAdj = new HashMap<>();
    public static Map<String, String> rightAdj = new HashMap<>();

    public static boolean isSatisficatory(List<String> nodes) {
        for (String node : nodes) {
            if (node.charAt(2) != 'Z') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        List<String> curNodes = parse();
        int n = curNodes.size();
        int step = 0;

        System.out.println(curNodes);

        while (!isSatisficatory(curNodes)) {
            char nextMove = directions.charAt(step % directions.length());

            for (int i = 0; i < n; i++) {
                String currentNode = curNodes.get(i);
                curNodes.set(i, nextMove == 'L' ? leftAdj.get(currentNode) : rightAdj.get(currentNode));
            }

            step++;
        }

        System.out.println(step);
    }

    public static List<String> parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/8.in"));
        directions = sc.nextLine();
        sc.nextLine();

        List<String> startingNodes = new ArrayList<>();

        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String node = st.nextToken();
            st.nextToken();
            String left = st.nextToken().substring(1, 4);
            String right = st.nextToken().substring(0, 3);
            leftAdj.put(node, left);
            rightAdj.put(node, right);

            if (node.charAt(2) == 'A') {
                startingNodes.add(node);
            }
        }

        return startingNodes;
    }
}
