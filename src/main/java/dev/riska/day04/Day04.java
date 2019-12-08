package dev.riska.day04;

import dev.riska.Utils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {

    private static final int MIN = 367479;
    private static final int MAX = 893698;

    public Day04() {
        Utils.writeResults("04", String.valueOf(part1()), String.valueOf(part2()));
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

    private int part2() {
        List<String> collect = IntStream.rangeClosed(MIN, MAX).mapToObj(String::valueOf)
                .filter(i -> containsExactlyTwoConsecutiveDuplicateNumbers(i) && allNumbersIncrease(i))
                .collect(Collectors.toList());
        return collect.size();
    }

    private boolean containsExactlyTwoConsecutiveDuplicateNumbers(String i) {
        return Arrays.stream(String.valueOf(i).split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .containsValue(2L);
    }

    private boolean allNumbersIncrease(String i) {
        return Arrays.stream(i.split(""))
                .sorted()
                .collect(Collectors.joining()).equals(i);
    }

    // My first solution to check for increasing digits
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
