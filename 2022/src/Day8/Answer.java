package src.Day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static Forrest loadData(BufferedReader reader) throws IOException {
        List<List<Integer>> data = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            List<Integer> row = new ArrayList<>();

            for (char treeHeight : line.toCharArray()) {
                try {
                    row.add(Integer.parseInt(treeHeight + ""));
                } catch (NumberFormatException ex) {
                    System.out.println("Error parsing " + treeHeight + " as Integer");
                }
            }
            data.add(row);
        }

        int[][] treeData = data.stream().map(n -> n.stream().mapToInt(i->i).toArray()).toArray(int[][]::new);
        return new Forrest(treeData);
    }
    
    @Override
    protected
    void solve(BufferedReader reader) throws IOException {
        Forrest forrest = loadData(reader);
        forrest.explore();

        System.out.println("Part 1: " + forrest.visibleTrees); // 1789
        System.out.println("Part 2: " + forrest.maxScenicScore); // 314820
    }
}
