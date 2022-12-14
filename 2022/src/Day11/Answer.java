package src.Day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static List<Monkey> loadMonkeys(BufferedReader reader) throws IOException {

        List<Monkey> monkeys = new LinkedList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                continue;
            }
            // Read items
            line = reader.readLine();
            List<BigInteger> items = new LinkedList<>();
            String[] stringItems = line.split("\\D+");
            for (String stringItem : stringItems) {
                if (stringItem.length() == 0) {
                    continue;
                }
                items.add(BigInteger.valueOf(Integer.parseInt(stringItem)));
            }
            // Read operation
            line = reader.readLine();
            Function<BigInteger, BigInteger> operation;
            String[] stringOperation = line.split("[^+*\\d]+");
            if (stringOperation.length <= 2) {
                if (stringOperation[1].equals("*")) {
                    operation = (item) -> {
                        return item.multiply(item);
                    };
                } else {
                    operation = (item) -> {
                        return item.add(item);
                    };

                }
            } else {
                int operationNumber = Integer.parseInt(stringOperation[2]);
                if (stringOperation[1].equals("*")) {
                    operation = (item) -> {
                        return item.multiply(BigInteger.valueOf(operationNumber));
                    };
                } else {
                    operation = (item) -> {
                        return item.add(BigInteger.valueOf(operationNumber));
                    };
                }
            }
            // Read worry test
            line = reader.readLine();
            int worryTest = Integer.parseInt(line.split("\\D+")[1]);
            line = reader.readLine();
            int trueMonkey = Integer.parseInt(line.split("\\D+")[1]);
            line = reader.readLine();
            int falseMonkey = Integer.parseInt(line.split("\\D+")[1]);

            Monkey monkey = new Monkey(items, operation, worryTest, trueMonkey, falseMonkey);

            monkeys.add(monkey);
        }

        return monkeys;
    }

    private BigInteger calculateMonkeyBusiness(List<Monkey> monkeys, int rounds, Function<BigInteger, BigInteger> coolWorryLevel) {
        for (int round = 0; round < rounds; round++) {
            for (Monkey monkey : monkeys) {
                if (round == 0) {
                    monkey.setCoolWorryLevel(coolWorryLevel);
                }
                for (BigInteger item : monkey.items) {
                    BigInteger itemToPass = monkey.inspectItem(item);
                    int monkeyToCatch = monkey.throwItem(itemToPass);
                    monkeys.get(monkeyToCatch).catchItem(itemToPass);
                }
                monkey.clearItems();
            }
        }

        PriorityQueue<BigInteger> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (Monkey monkey : monkeys) {
            maxHeap.add(monkey.inspectionCount);
        }

        BigInteger monkeyBusiness = maxHeap.poll().multiply(maxHeap.poll());
        return monkeyBusiness;
    }

    private BigInteger calculateLCM(List<Monkey> monkeys) {
        int lcm = 1;
        for (Monkey monkey : monkeys) {
            lcm *= monkey.worryTest;
        }
        BigInteger bigLcm = BigInteger.valueOf(lcm);
        return bigLcm;
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        List<Monkey> monkeys = loadMonkeys(reader);
        List<Monkey> monkeysCopy = new LinkedList<>();
        for(Monkey monkey : monkeys) {
            Monkey copy = monkey.clone();
            monkeysCopy.add(copy);
        }
        BigInteger monkeyBusiness = calculateMonkeyBusiness(monkeys, 20, (num) -> num.divide(BigInteger.valueOf(3)));

        BigInteger lcm = calculateLCM(monkeysCopy);
        BigInteger monkeyBusiness2 = calculateMonkeyBusiness(monkeysCopy, 10000, (num) -> num.mod(lcm));

        
        System.out.println("Part 1: " + monkeyBusiness); // 50,830 
        System.out.println("Part 2: " + monkeyBusiness2); // 14,399,640,002
    }
}
