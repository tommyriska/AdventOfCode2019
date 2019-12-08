package dev.riska.day01;

import dev.riska.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Day01 {

    private static final String FILENAME = "Day01Input.txt";

    public Day01() {
        Utils.writeResults("01", String.valueOf(part1()), String.valueOf(part2()));
    }

    private double part1() {
        return Utils.getInput(FILENAME).stream()
                .map(BigDecimal::new)
                .map(this::calculate)
                .map(BigDecimal::doubleValue)
                .reduce(0d, Double::sum);
    }

    private double part2() {
        return Utils.getInput(FILENAME).stream()
                .map(BigDecimal::new)
                .map(this::calculate)
                .map(BigDecimal::doubleValue)
                .map(this::calculatePart2)
                .reduce(0d, Double::sum);
    }

    private double calculatePart2(double fuel) {
        return fuel + recursive(fuel);
    }

    private double recursive(double fuel) {
        double t = calculate(new BigDecimal(fuel)).doubleValue();
        if (t > 0) {
            return t + recursive(t);
        }
        return 0;
    }

    private BigDecimal calculate(BigDecimal mass) {
        return mass.divide(new BigDecimal(3), RoundingMode.DOWN).subtract(new BigDecimal(2));
    }
}
