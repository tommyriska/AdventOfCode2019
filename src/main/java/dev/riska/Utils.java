package dev.riska;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<String> getInput(String filename) {
        try {
            return Files.lines(Paths.get(ClassLoader.getSystemResource(filename).toURI())).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void writeResults(String dayNr, String part1, String part2) {

        System.out.printf("========== Day %s ==========\n", dayNr);
        System.out.printf("Answer part 1: %s\n", part1);
        System.out.printf("Answer part 2: %s\n", part2);
        System.out.println("============================\n");
    }
}
