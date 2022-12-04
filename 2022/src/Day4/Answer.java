package src.Day4;

import java.io.BufferedReader;
import java.io.IOException;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static int[] getBounds(String pair) {
        String[] stringBounds = pair.split("-");
        int lower = Integer.parseInt(stringBounds[0]);
        int upper = Integer.parseInt(stringBounds[1]);
        return new int[]{lower, upper};
    }
    
    private static boolean isCompleteOverlap(int[] shift1, int[] shift2) {
        return shift1[0] >= shift2[0] && shift1[1] <= shift2[1];
    }

    private static boolean isOverlap(int[] shift1, int[] shift2) {
        return (shift1[0] >= shift2[0] && shift1[0] <= shift2[1]);
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        int completeOverlap = 0;
        int overlap = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] pairs = line.split(",");
            int[] shift1 = getBounds(pairs[0]);
            int[] shift2 = getBounds(pairs[1]);

            if (isCompleteOverlap(shift1, shift2) || isCompleteOverlap(shift2, shift1)) {
                completeOverlap++;
            }
            if (isOverlap(shift1, shift2) || isOverlap(shift2, shift1)) {
                overlap++;
            }
        }
        
        System.out.println("Part 1: " + completeOverlap); // 456
        System.out.println("Part 2: " + overlap); // 808
    }

}
