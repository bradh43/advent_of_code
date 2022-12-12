package src.Day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private ArrayList<Integer> loadProgramHistory(BufferedReader reader) throws IOException {
        
        int x = 1;
        ArrayList<Integer> history = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line != null) {
                String[] ops = line.split("\s");
                String op = ops[0];
                if (ops[0].equals("noop")) {
                    history.add(x);
                    continue;
                }
                if (op.equals("addx")) {
                    int arg = Integer.parseInt(ops[1]);
                    history.add(x);
                    history.add(x);
                    x += arg;
                }
            }
        }
        return history;
    }

    private static int calculateSignalStrength(ArrayList<Integer> history) {
        int strength = 0;

        for (int cycle = 20; cycle <= 220; cycle += 40) {
            strength += history.get(cycle - 1) * cycle;
        }
        return strength;
    }

    private static void drawCRT(ArrayList<Integer> history) {

        System.out.println();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 40; col++) {
                int crt = row * 40 + col;
                int spriteIndex = history.get(crt % history.size());
                // System.out.println("crt: " + crt % 40 + ", sprite: " + spriteIndex);
                if (Math.abs(spriteIndex - (crt % 40)) <= 1) {
                    System.out.print((char) 9608);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {
        ArrayList<Integer> history = loadProgramHistory(reader);

        System.out.println("Part 1: " + calculateSignalStrength(history)); // 14320

        System.out.println("Part 2: PCPBKAPJ"); // PCPBKAPJ
        drawCRT(history);
    }

}