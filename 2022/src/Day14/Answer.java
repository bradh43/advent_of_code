package src.Day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import src.Solution;
import src.Day9.Position;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    // rock as #, air as ., sand as +,
    private static final char ROCK = '#';
    private static final char AIR = '.';
    private static final char SAND = '+';

    
    private static Position DOWN = new Position(0, 1);
    private static Position LEFT = new Position(-1, 1);
    private static Position RIGHT = new Position(1, 1);

    private static Position[] directions = {
        DOWN,
        LEFT,
        RIGHT
    };

    private static final Position ENTRY_POINT = new Position(500 + 1, 0);

    private static int minX = Integer.MAX_VALUE;
    private static int maxX = Integer.MIN_VALUE;
    private static int minY = 0;
    private static int maxY = Integer.MIN_VALUE;

    private static List<List<Position>> loadRockLines(BufferedReader reader) throws IOException {
        List<List<Position>> rockLines = new LinkedList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] rockStrings = line.split("\\s\\-\\>\\s");
            List<Position> rockCorners = new LinkedList<>();
            for (String rockString : rockStrings) {
                String[] coordinateString = rockString.split(",");
                int x = Integer.parseInt(coordinateString[0]);
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                int y = Integer.parseInt(coordinateString[1]);
                maxY = Math.max(maxY, y);
                
                Position position = new Position(x + 1, y);
                rockCorners.add(position);
            }
            rockLines.add(rockCorners);
        }
        minX -= maxY * 2;
        maxX += maxY * 2;
        return rockLines;
    }

    private static void fillLine(char[][] grid, Position previous, Position current) {
        for (int x = current.x; x <= previous.x; x++) {
            for (int y = previous.y; y <= current.y; y++) {
                grid[y][x - minX] = ROCK;
            }
            for (int y = current.y; y <= previous.y; y++) {
                grid[y][x - minX] = ROCK;
            }
        }
        for (int x = previous.x; x <= current.x; x++) {
            for (int y = previous.y; y <= current.y; y++) {
                grid[y][x - minX] = ROCK;
            }
            for (int y = current.y; y <= previous.y; y++) {
                grid[y][x - minX] = ROCK;
            }
        }

    }

    private static char[][] loadGrid(List<List<Position>> rockLines) {
        int rowSize = maxY - minY + 2;
        int colSize = maxX - minX + 3;
        char[][] grid = new char[rowSize][colSize];
        for (char[] row: grid) {
            Arrays.fill(row, AIR);
        }
        for (List<Position> rockLine : rockLines) {
            Position previous = null;
            for (Position current: rockLine) {
                if (previous != null) {
                    fillLine(grid, previous, current);
                }
                previous = current;
            }
        }
        return grid;
    }

    private static void printGrid(char[][] grid) {
        grid[ENTRY_POINT.y][ENTRY_POINT.x - minX] = 'S';
        System.out.println();
        for (char[] row: grid) {
            for (char display : row) {
                System.out.print(display);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(char[][] grid, Position position) {
        return position.x - minX >= 0 && position.x - minX < grid[0].length && position.y >= 0 && position.y < grid.length;
    }

    private static boolean pourSand(char[][] grid, BiPredicate<char[][], Position> isAbyss) {
        grid[ENTRY_POINT.y][ENTRY_POINT.x - minX] = SAND;
        Position location = ENTRY_POINT;
        boolean canSandFall = true;
        while (canSandFall) {
            if (grid[1][500-minX] == SAND && grid[1][502-minX] == SAND) {
                System.out.println(location);
                return false;
            }
            if (grid[location.y][location.x - minX] != ROCK) {
                grid[location.y][location.x - minX] = SAND;
            }
            Position checkLocation;
            for (Position direction : directions) {
                checkLocation = location.apply(direction);
                canSandFall = true;
                if (isAbyss.test(grid, checkLocation)) {
                    return false;
                }
                if (isInBounds(grid, checkLocation) && grid[checkLocation.y][checkLocation.x - minX] == AIR) {
                    if (grid[location.y][location.x - minX] != ROCK) {
                        grid[location.y][location.x - minX] = AIR;
                        location = checkLocation;
                        break;
                    }
                }
                canSandFall = false;
            }
        }

        return true;
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {
        List<List<Position>> rockLines = loadRockLines(reader);
        char[][] grid = loadGrid(rockLines);
        // printGrid(grid);
        
        BiPredicate<char[][], Position> isAbyss = (positions, position) -> {
            return position.y == positions.length - 1;
        };
        
        int count = 0;
        while (pourSand(grid, isAbyss)) {
            count++;
        }


        BiPredicate<char[][], Position> isAbyss2 = (positions, position) -> {
            return position.x - minX == 0 || position.x - minX == positions[0].length - 1;
        };

        int count2 = count + 3;
        while (pourSand(grid, isAbyss2)) {
            count2++;
        }
        System.out.println("Part 1: " + count); // 825
        System.out.println("Part 2: " + count2); // 26729
    }
}
