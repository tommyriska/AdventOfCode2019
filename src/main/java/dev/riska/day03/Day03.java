package dev.riska.day03;

import dev.riska.Utils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {

    public static final String FILENAME1 = "Day03Input1.txt";
    public static final String FILENAME2 = "Day03Input2.txt";
    public static final Map<String, Integer> DIR_X = new HashMap<>();
    public static final Map<String, Integer> DIR_Y = new HashMap<>();

    public Day03() {
        part1();
    }


    private void part1() {
        setDirRules();

        List<String> wire1Dirs = getDirList(Utils.getInput(FILENAME1).get(0));
        List<String> wire2Dirs = getDirList(Utils.getInput(FILENAME2).get(0));

        Set<Integer> w1 = getPoints(wire1Dirs);
        Set<Integer> w2 = getPoints(wire2Dirs);

        Iterator<Integer> it1 = w1.iterator();
        Iterator<Integer> it2 = w2.iterator();

        int min;
        while (it1.hasNext() && it2.hasNext()) {
            int w1x =
        }

        both.stream().min(Integer::compareTo);
    }

    private Set<Integer> getPoints(List<String> wireDirs) {
        int x = 0;
        int y = 0;
        Set<Integer> dirSet = new HashSet<>();
        dirSet.add((x + y));
        for (String s : wireDirs) {
            String dir = String.valueOf(s.charAt(0));
            int amount = Integer.parseInt(s.substring(1, s.length()));
            for (int i = 0; i < amount; i++) {
                x += DIR_X.get(dir);
                y += DIR_Y.get(dir);
                dirSet.add((x,y));
            }
        }
        return dirSet;
    }

    private List<String> getDirList(String wire) {
        return Stream.of(wire.split(",")).collect(Collectors.toList());
    }

    private void setDirRules() {
        DIR_X.put("L", -1);
        DIR_X.put("R", 1);
        DIR_X.put("U", 0);
        DIR_X.put("D", 0);
        DIR_Y.put("L", 0);
        DIR_Y.put("R", 0);
        DIR_Y.put("U", 1);
        DIR_Y.put("D", -1);
    }
}
