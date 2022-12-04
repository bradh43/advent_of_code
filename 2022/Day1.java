import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Day1 extends BaseDay {

    @Override
    void solve(BufferedReader reader) throws IOException {
        int sumSize = 3;
        int sum = 0;

        PriorityQueue<Integer> topSums = new PriorityQueue<Integer>();
        topSums.add(Integer.MIN_VALUE);

        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                int num = Integer.parseInt(line);
                sum += num;
                continue;
            }
            // add sum to priority queue if more than lowest sum
            if (sum >= topSums.peek()) {
                topSums.add(sum);
            }
            // keep priority queue the correct size
            if (topSums.size() > sumSize) {
                topSums.remove();
            }
            sum = 0;
        }
        int max = -1;
        int topSum = 0;
        while (!topSums.isEmpty()) {
            int num = topSums.poll();
            topSum += num;
            // max is going to be the last number in the PriorityQueue
            max = num;
        }
        
        System.out.println("Part 1: " + max); // 69206
        System.out.println("Part 2: " + topSum); // 197400
        
    }

}