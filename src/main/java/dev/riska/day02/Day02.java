package dev.riska.day02;

import dev.riska.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02 {

    private static final String FILENAME = "Day02Input.txt";

    public Day02() {
        System.out.println("\n======== Day 02 ========");
        System.out.printf("Answer part 1: %s\n", part1());
        System.out.printf("Answer part 2: %s\n", part2());
    }

    private int part1() {
        String line = Utils.getInput(FILENAME).get(0);
        List<Integer> program = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        program.set(1, 12);
        program.set(2, 2);
        return runProgram(program);
    }

    private int part2() {
        String line = Utils.getInput(FILENAME).get(0);
        List<Integer> program = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < 100; i++) {
            program.set(1, i);
            for (int j = 0; j < 100; j++) {
                program.set(2, j);
                if (runProgram(new ArrayList<>(program)) == 19690720) {
                    return ((100 * i) + j);
                }
            }
        }
        return 0;
    }

    private int runProgram(List<Integer> program) {
        for (int i = 0; i < program.size(); i++) {
            switch (program.get(i)) {
                case 1:
                    program.set(program.get(i + 3), program.get(program.get(i + 1)) + program.get(program.get(i + 2)));
                    i += 3;
                    break;
                case 2:
                    program.set(program.get(i + 3), program.get(program.get(i + 1)) * program.get(program.get(i + 2)));
                    i += 3;
                    break;
                case 99:
                    return program.get(0);
            }
        }
        return program.get(0);
    }
}
