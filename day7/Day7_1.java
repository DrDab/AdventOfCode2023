package day7;

import java.util.*;
import java.io.*;

public class Day7_1 {
    public static final char[] cardHierarchy = new char[] { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3',
            '2' };

    private static class Hand implements Comparable<Hand> {
        public String cards;
        public int bid;
        public int handHierarchy;

        public Hand(String cards, int bid, int handHierarchy) {
            this.cards = cards;
            this.bid = bid;
            this.handHierarchy = handHierarchy;
        }

        @Override
        public int compareTo(Hand other) {
            if (handHierarchy != other.handHierarchy) {
                return handHierarchy - other.handHierarchy;
            }

            for (int i = 0; i < 5; i++) {
                char c0 = cards.charAt(i);
                char c1 = other.cards.charAt(i);

                if (c0 != c1) {
                    int r0 = 0;
                    int r1 = 0;

                    for (; r0 < cardHierarchy.length; r0++) {
                        if (cardHierarchy[r0] == c0) {
                            break;
                        }
                    }

                    for (; r1 < cardHierarchy.length; r1++) {
                        if (cardHierarchy[r1] == c1) {
                            break;
                        }
                    }

                    return r1 - r0;
                }
            }

            return 0;
        }
    }

    public static List<Hand> hands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        parse();

        Collections.sort(hands);

        long sol = 0L;

        for (int i = 0; i < hands.size(); i++) {
            sol += (i+1) * hands.get(i).bid;
        }

        System.out.println(sol);
    }

    public static void parse() throws IOException {
        Scanner sc = new Scanner(new File("inputs/7.in"));

        Map<Character, Integer> count = new HashMap<>();

        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String cards = st.nextToken();
            int bid = Integer.parseInt(st.nextToken());

            for (int i = 0; i < cards.length(); i++) {
                char c = cards.charAt(i);
                count.put(c, count.containsKey(c) ? count.get(c) + 1 : 1);
            }

            int handHierarchy = 0;

            if (count.size() == 1) {
                // Five of a kind.
                handHierarchy = 6;
            }

            if (count.size() == 2) {
                // Full house or four of a kind.
                for (Character card : count.keySet()) {
                    if (count.get(card) == 4) {
                        // Four of a kind.
                        handHierarchy = 5;
                        break;
                    }
                }

                // Not four of a kind => full house.
                if (handHierarchy != 5) {
                    handHierarchy = 4;
                }
            }

            if (count.size() == 3) {
                // Three of a kind or two pair.
                for (Character card : count.keySet()) {
                    if (count.get(card) == 3) {
                        // Three of a kind.
                        handHierarchy = 3;
                        break;
                    }
                }

                // Not three of a kind => two pair.
                if (handHierarchy != 3) {
                    handHierarchy = 2;
                }
            }

            if (count.size() == 4) {
                // One pair.
                handHierarchy = 1;
            }

            hands.add(new Hand(cards, bid, handHierarchy));
            count.clear();
        }
    }
}
