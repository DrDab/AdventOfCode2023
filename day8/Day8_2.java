package day8;

import java.util.*;
import java.io.*;

public class Day8_2 {
    public static String directions;
    public static Map<String, String> leftAdj = new HashMap<>();
    public static Map<String, String> rightAdj = new HashMap<>();

    public static boolean isSatisficatory(String node) {
        return node.charAt(2) == 'Z';
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(List<Long> vals) {
        long lcm = vals.get(0);

        for (int i = 1; i < vals.size(); i++) {
            long val = vals.get(i);
            lcm *= val / gcd(lcm, val);
        }

        return lcm;
    }

    public static void main(String[] args) throws IOException {
        List<String> curNodes = parse();
        List<Long> numSteps = new ArrayList<>();

        for (int i = 0; i < curNodes.size(); i++) {
            String currentNode = curNodes.get(i);
            long step = 0L;

            while (!isSatisficatory(currentNode)) {
                char nextMove = directions.charAt((int) (step % directions.length()));
                currentNode = nextMove == 'L' ? leftAdj.get(currentNode) : rightAdj.get(currentNode);
                step++;
            }

            numSteps.add(step);
        }

        System.out.println(lcm(numSteps));
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
