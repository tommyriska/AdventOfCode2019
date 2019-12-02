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
}
