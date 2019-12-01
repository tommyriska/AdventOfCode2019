package dev.riska.Day01;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {

    private static final String FILENAME = "Day01Input.txt";

    public Day01() {
        double answerPart1 = getInput().stream()
                .map(BigDecimal::new)
                .map(this::calculate)
                .map(BigDecimal::doubleValue)
                .reduce(0d, Double::sum);
        System.out.printf("Part 1: %s\n", answerPart1);

        double answerPart2 = getInput().stream()
                .map(BigDecimal::new)
                .map(this::calculate)
                .map(BigDecimal::doubleValue)
                .map(this::calculatePart2)
                .reduce(0d, Double::sum);
        System.out.printf("Part 2: %s\n", answerPart2);
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

    private List<String> getInput() {
        try {
            return Files.lines(Paths.get(ClassLoader.getSystemResource(FILENAME).toURI())).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
