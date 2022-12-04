package src.Day2;

import java.io.BufferedReader;
import java.io.IOException;

import src.Solution;


public class Answer extends Solution {

    public Answer() {
        super();
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        int total = 0;
        int total2 = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            RPS rps = new RPS(line);
            total += rps.score();
            total2 += rps.score2();
        }
        
        System.out.println("Part 1: " + total); // 17189
        System.out.println("Part 2: " + total2); // 13490
    }

}
