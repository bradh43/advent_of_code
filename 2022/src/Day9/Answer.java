package src.Day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super("bigSample");
        // super(true);
        // super();
    }

    private static List<Vector> loadData(BufferedReader reader) throws IOException {

        List<Vector> directions = new ArrayList<Vector>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineString = line.split("\\s");

            char direction = lineString[0].charAt(0);
            int magnitude = Integer.parseInt(lineString[1]);

            directions.add(new Vector(direction, magnitude));
        }
        return directions;
    }

    private static void printRope(Position[] rope, int boardSize) {
        char[][] board = new char[boardSize][boardSize];
        int padding = (boardSize / 2) - 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }

        board[padding][padding] = 's';

        int i = 0;
        for (Position segment : rope) {
            int x = segment.x + padding;
            int y = segment.y + padding;
            char display = i == 0 ? 'H' : (char)(i+'0');
            if (board[x][y] == '.' || board[x][y] == 's') {
                board[x][y] = display;
            }
            i++;
        }


        for (char[] row : board) {
            for (char piece : row) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println();

    }

    private static void printMotion(HashSet<String> visited, int boardSize) {
        char[][] board = new char[boardSize][boardSize];
        int padding = (boardSize / 2) - 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = i == padding && j == padding ? 's' : '.';
            }
        }

        for (String position : visited) {
            String[] positionString = position.split(",");
            int x = Integer.parseInt(positionString[0]) + padding;
            int y = Integer.parseInt(positionString[1]) + padding;
            board[x][y] = board[x][y] == 's' ? 's' : '#';
        }


        for (char[] row : board) {
            for (char piece : row) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println();

    }

    private void countKnotVisit(Position[] rope, int i, HashSet<String> part1, HashSet<String> part2) {
        if (i == 0) {
            part1.add(rope[i + 1].toString());
        }
        if (i == rope.length - 2) {
            part2.add(rope[i+1].toString());
        }
    }


    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        List<Vector> directions = loadData(reader);

        int ropeLength = 10;

        Position[] rope = new Position[ropeLength];
        Arrays.fill(rope, new Position(0, 0));

        HashSet<String> part1 = new HashSet<>();
        HashSet<String> part2 = new HashSet<>();

        Position start = new Position(0, 0);
        part1.add(start.toString());
        part2.add(start.toString());

        
        for (Vector vector : directions) {
            // printRope(rope, 30);
            
            for (int i = 0; i < rope.length - 1; i++) {
                if (i == 0) {
                    rope[i] = rope[i].apply(vector.direction);
                }
    
                while (!rope[i + 1].isTouching(rope[i])) {
                    rope[i + 1] = rope[i + 1].moveTowards(rope[i]);
                    countKnotVisit(rope, i, part1, part2);            
                }
            }
            
        }
        // printRope(rope, 40);
        // printMotion(part2, 30);
        
        System.out.println("Part 1: " + part1.size()); // 5683
        System.out.println("Part 2: " + part2.size()); // 2372
    }

}
