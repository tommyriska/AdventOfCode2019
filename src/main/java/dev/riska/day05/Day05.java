package dev.riska.day05;

import dev.riska.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05 {

    private static final String FILENAME = "Day05Input.txt";
    private static final int INPUT_VALUE_PART_1 = 1;
    private static final int INPUT_VALUE_PART_2 = 5;

    public Day05() {
//        Utils.writeResults("05", String.valueOf(part1()), String.valueOf(part2()));
    part2();
    }

    private int part1() {
        System.out.println("PART 1");
        String line = Utils.getInput(FILENAME).get(0);
        List<Integer> program = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return runProgram(program, INPUT_VALUE_PART_1);
    }

    // Wrong answers: 3, 0,
    private int part2() {
        System.out.println("\nPART 2");
//        String line = Utils.getInput(FILENAME).get(0);
        String line = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        List<Integer> program = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return runProgram(program, INPUT_VALUE_PART_2);
    }

    // Int Code Machine
    private int runProgram(List<Integer> program, int inputValue) {

        int output = 0;
        int i = 0;
//        program = Arrays.asList(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9);
//        for (int i = 0; i < program.size(); i++) {
        rootLoop:
        while(i < program.size()) {
            System.out.println(i);
            // Calculate mode/op
            int opCode = program.get(i) % 100;
            int firstParamMode = program.get(i) / 100 % 10;
            int secondParamMode = program.get(i) / 1000 % 10;

            switch (opCode) {
                case 1:
                    program.set(program.get(i + 3), (firstParamMode == 0 ? program.get(program.get(i + 1)) : program.get(i + 1))
                            + (secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2)));
                    i += 4;
                    break;
                case 2:
                    program.set(program.get(i + 3), (firstParamMode == 0 ? (program.get(program.get(i + 1))) : program.get(i + 1))
                            * (secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2)));
                    i += 4;
                    break;
                case 3:
                    program.set(program.get(i + 1), inputValue);
                    i += 2;
                    break;
                case 4:
                    // output the value at the index of the only parameter we get
                    output = program.get(program.get(i + 1));
                    System.out.println("Output: " + output);
                    i += 2;
                    break;
                case 5:
                    if ((firstParamMode == 0) ? (program.get(program.get(i + 1)) != 0) : (program.get(i + 1) != 0)) {
                        i = (secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2));
                    } else {
                        i += 3;
                    }
                    break;
                case 6:
                    if ((firstParamMode == 0) ? (program.get(program.get(i + 1)) == 0) : (program.get(i + 1) == 0)) {
                        i = (secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2));
                    } else {
                        i += 3;
                    }
                    break;
                case 7:
                    if ((firstParamMode == 0 ? program.get(program.get(i + 1)) : program.get(i + 1)) < (secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2))) {
                        program.set(program.get(i + 3), 1);
                    } else {
                        program.set(program.get(i + 3), 0);
                    }
                    i += 4;
                    break;
                case 8:
                    if ((firstParamMode == 0 ? program.get(program.get(i + 1)) : program.get(i + 1)).equals(secondParamMode == 0 ? program.get(program.get(i + 2)) : program.get(i + 2))) {
                        program.set(program.get(i + 3), 1);
                    } else {
                        program.set(program.get(i + 3), 0);
                    }
                    i += 4;
                    break;
                case 99:
                    System.out.println("halt");
                    break rootLoop;
            }
        }
        return output;
    }
}
