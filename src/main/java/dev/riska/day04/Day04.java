package dev.riska.day04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    private static final int MIN = 367479;
    private static final int MAX = 893698;
    private static final Pattern ADJACENT_DIGITS = Pattern.compile("(.)\\1+");


    public Day04() {
        System.out.println("\n======== Day 04 ========");
//        System.out.printf("Answer part 1: %s\n", part1());
        System.out.printf("Answer part 2: %s\n", part2());
    }

    private int part2() {
        Set<Integer> pwMatches = new HashSet<>();
        for (int i = MIN; i < MAX; i++) {
            // Check that the number contains two adjacent digits
            if (extendedAdjacentDigits(i)) {
                // Check that the value of each digit is more than the one before
                if (digitsInIncreasingOrder(i)) {
                    pwMatches.add(i);
                }
            }
        }
        return pwMatches.size();
    }

    private int part1() {
        Set<Integer> pwMatches = new HashSet<>();
        for (int i = MIN; i < MAX; i++) {
            // Check that the number contains two adjacent digits
            if (adjacentDigits(i)) {
                // Check that the value of each digit is more than the one before
                if (digitsInIncreasingOrder(i)) {
                    pwMatches.add(i);
                }
            }
        }
        return pwMatches.size();
    }

    private boolean digitsInIncreasingOrder(int number) {
        boolean digitsInIncreasingOrder = true;
        char[] digits = String.valueOf(number).toCharArray();
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i + 1] < digits[i]) {
                digitsInIncreasingOrder = false;
                break;
            }
        }
        return digitsInIncreasingOrder;
    }

    private boolean extendedAdjacentDigits(int number) {
        boolean correctAdjacentDigits = false;
        char[] chars = String.valueOf(number).toCharArray();
        Map<Integer, Integer> count = new HashMap<>();
        for (char c : chars) {
            count.put((int) c, 0);
        }
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count.put((int) chars[i], count.get(chars[i] + 1));
            }
        }

        for (Integer c : count.values()) {
            if (c.compareTo(2) == 0) {
                correctAdjacentDigits = true;
            }
        }

        return correctAdjacentDigits;
    }

    private boolean adjacentDigits(int number) {
        boolean adjacentDigits = false;
        char[] chars = String.valueOf(number).toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                adjacentDigits = true;
                break;
            }
        }
        return adjacentDigits;
    }
}
