package src.Day5;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Instruction {

    int crateCount;
    int origin;
    int destination;

    public Instruction(String line) {
        String[] numbers = Pattern.compile("\\d+")
            .matcher(line)
            .results()
            .map(MatchResult::group)
            .toArray(String[]::new);
        
        this.crateCount = Integer.parseInt(numbers[0]);
        this.origin = Integer.parseInt(numbers[1]);
        this.destination = Integer.parseInt(numbers[2]);
    }

    @Override
    public String toString() {
        return "move " + this.crateCount + " from " + this.origin + " to " + this.destination;
    }
}
