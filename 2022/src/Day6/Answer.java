package src.Day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static boolean hasDuplicates(char[] window) {
        HashSet<Character> set = new HashSet<>();
        for (char mark : window) {
            if (!set.add(mark)) {
                return true;
            }
        }
        return false;
    }

    private static int findFirstMarker(String stream, int markerLimit) {

        char[] window = new char[markerLimit];
        int i = 0;

        for (char marker : stream.toCharArray()) {
            window[i++ % markerLimit] = marker;
            
            if (i >= markerLimit && !hasDuplicates(window)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        int firstMarker = -1;
        int firstMarker2 = -1;

        String line;
        while ((line = reader.readLine()) != null) {
            firstMarker = findFirstMarker(line, 4);
            firstMarker2 = findFirstMarker(line, 14);
        }
        
        System.out.println("Part 1: " + firstMarker); // 1929
        System.out.println("Part 2: " + firstMarker2); // 3298
    }

}
