package src.Day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import src.Solution;
import src.Day9.Position;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static Position[] directions = {
        new Position(-1, 0), // Up
        new Position(1, 0), // Down
        new Position(0, -1), // Right
        new Position(0, 1), // Left
    };

    private static Cell[][] loadGrid(BufferedReader reader) throws IOException {
        
        List<List<Cell>> grid = new LinkedList<>();
         
        String line;
        while ((line = reader.readLine()) != null) {
            List<Cell> row = new LinkedList<>();
            for (char elevation : line.toCharArray()) {
                row.add(new Cell(elevation));
            }
            grid.add(row);
        }
        return grid.stream().map(row -> row.stream().toArray(Cell[]::new)).toArray(Cell[][]::new);
    }

    private static boolean isInBounds(Cell[][] grid, Position position) {
        return position.x >= 0 && position.x < grid.length && position.y >= 0 && position.y < grid[0].length;
    }

    private static int bfs(Cell[][] grid, Position start) {

        Queue<Position> queue = new LinkedList<>();
        Queue<Integer> stepsQueue = new LinkedList<>();

        HashSet<String> visited = new HashSet<>();
        visited.add(start.toString());
        queue.add(start);
        stepsQueue.add(0);

        while (!queue.isEmpty()) {
            Position cell = queue.poll();
            int steps = stepsQueue.poll();
            if (grid[cell.x][cell.y].isEnd) {
                return steps;
            }

            for (Position direction : directions) {
                Position nextMove = cell.apply(direction);
                if (isInBounds(grid, nextMove) 
                    && grid[cell.x][cell.y].canVisit(grid[nextMove.x][nextMove.y]) 
                    && !visited.contains(nextMove.toString())
                ) {
                    visited.add(nextMove.toString());
                    queue.add(nextMove);
                    stepsQueue.add(steps + 1);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static Position findStart(Cell[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y].isStart) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    private static int findShortestStart(Cell[][] grid) {
        int min = Integer.MAX_VALUE;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y].elevation == 0) {
                    Position newStart = new Position(x, y);
                    int startSteps = bfs(grid, newStart);
                    min = Math.min(min, startSteps);
                }
            }
        }
        return min;
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        Cell[][] grid = loadGrid(reader);

        Position start = findStart(grid);
        int steps = bfs(grid, start);

        int min = findShortestStart(grid);

        System.out.println("Part 1: " + steps); // 517
        System.out.println("Part 2: " + min); // 512
    }

}
