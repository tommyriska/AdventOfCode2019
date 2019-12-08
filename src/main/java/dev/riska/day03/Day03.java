package dev.riska.day03;

import dev.riska.Utils;

import java.util.HashMap;
import java.util.Map;

// Shameless copy from /r/rowdyruski, but instead of loading puzzle input via system in I get it from a txt file in the resources.
public class Day03 {

    public static final String FILENAME1 = "Day03Input1.txt";
    public static final String FILENAME2 = "Day03Input2.txt";

    public Day03() {
        String[] input = Utils.getInput(FILENAME1).get(0).split(",");

        Map<String, Integer> wire = new HashMap<>();
        int closestDistance = Integer.MAX_VALUE;
        int shortestWire = Integer.MAX_VALUE;

        int x = 0, y = 0, d = 0;

        for (int i = 0; i < input.length; i++) {
            int[] dir = getDir(input[i].charAt(0));
            int len = Integer.parseInt(input[i].substring(1));
            for (int j = 0; j < len; j++) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                wire.put(newX + "_" + newY, ++d);
                x = newX;
                y = newY;
            }
        }

        input = Utils.getInput(FILENAME2).get(0).split(",");
        x = y = d = 0;
        for (int i = 0; i < input.length; i++) {
            int[] dir = getDir(input[i].charAt(0));
            int len = Integer.parseInt(input[i].substring(1));
            for (int j = 0; j < len; j++) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                d++;

                if (wire.containsKey(newX + "_" + newY)) {
                    closestDistance = Math.min(closestDistance, (int) Math.abs(x) + (int) Math.abs(newY));
                    shortestWire = Math.min(shortestWire, wire.get(newX + "_" + newY) + d);
                }

                x = newX;
                y = newY;
            }
        }

        Utils.writeResults("03", String.valueOf(closestDistance), String.valueOf(shortestWire));
    }

    public static int[] getDir(char c) {
        switch (c) {
            case 'U':
                return new int[]{0, 1};
            case 'D':
                return new int[]{0, -1};
            case 'L':
                return new int[]{-1, 0};
            case 'R':
                return new int[]{1, 0};
        }
        return null;
    }
}
