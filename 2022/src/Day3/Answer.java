package src.Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    public static char defaultZeroChar = 'a' - 1;

    public static int getCharScore(char item) {
        if (Character.isUpperCase(item)) {
            return item - 'A' + 27;
        }
        return item - 'a' + 1;
    }

    public static int part1(String line) {
        int midPoint = line.length() / 2;
            
        String lower = line.substring(0, midPoint);
        String upper = line.substring(midPoint);
        
        HashSet<Character> set = new HashSet<>();
        for (char item : lower.toCharArray()) {
            set.add(item);
        }

        for (char item : upper.toCharArray()) {
            if (set.contains(item)) {
                return getCharScore(item);
            }
        }
        return 0;
    }

    public static int part2(String[] groups) {
        HashSet<Character> sharedSet = new HashSet<>();
        HashSet<Character> oldSet = new HashSet<>();
        for (int i = 0; i < groups.length; i++) {
            for (char item : groups[i].toCharArray()) {
                if (i == 0) {
                    sharedSet.add(item);
                    continue;
                }
                if (oldSet.contains(item)) {
                    sharedSet.add(item);
                }
            }
            oldSet = new HashSet<>(sharedSet);
            sharedSet = new HashSet<>();
        }

        char sharedItem = oldSet.stream().findFirst().orElse(defaultZeroChar);
        return getCharScore(sharedItem);

    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        int sum = 0;
        int sum2 = 0;

        int groupSize = 3;
        String[] group = new String[groupSize]; 
        int i = 0;
        
        String line;
        while ((line = reader.readLine()) != null) {
            sum += part1(line);
            group[i++ % groupSize] = line;
            if (i % groupSize == 0) {
                sum2 += part2(group);
            }
        }
        
        System.out.println("Part 1: " + sum); // 8039
        System.out.println("Part 2: " + sum2); // 2510
    }

}
