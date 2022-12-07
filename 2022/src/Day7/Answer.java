package src.Day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static Node loadOutput(BufferedReader reader) throws IOException {
        Pattern commandRegex = Pattern.compile("\\$\\s(\\w{2})\\s?(\\w+|\\/|\\.{2})?");
        Pattern dirRegex = Pattern.compile("dir\\s(\\w+)");
        Pattern fileRegex = Pattern.compile("(\\d+)\\s(\\w+.?\\w*)");

        Node root = null;
        Stack<Node> stack = new Stack<>();

        String line;
        while ((line = reader.readLine()) != null) {
            Matcher commandMatcher = commandRegex.matcher(line);
            if (commandMatcher.matches()) {
                String action = commandMatcher.group(1);
                switch (action) {
                    case "cd":
                        String file = commandMatcher.group(2);
                        switch (file) {
                            case "/":
                                root = new Node(file);
                                stack.add(root);
                                break;
                            case "..":
                                stack.pop();
                                break;
                            default:
                                Node node = new Node(file);
                                stack.peek().addFile(node);
                                stack.add(node);
                                break;
                        }
                        break;
                    case "ls":
                        continue;
                }
                continue;
            }
            Matcher dirMatcher = dirRegex.matcher(line);
            if (dirMatcher.matches()) {
                continue;
            }
            Matcher fileMatcher = fileRegex.matcher(line);
            if (fileMatcher.matches()) {
                int size = Integer.parseInt(fileMatcher.group(1));
                String file = fileMatcher.group(2);
                stack.peek().addFile(new Node(file, size));
                continue;
            }
        }
        return root;
    }

    private static int part1(Node system) {
        Predicate<Node> predicate = (node) -> node.getSize() <= 100000;
        BiFunction<Integer, Integer, Integer> sum = (x, y) -> x + y;
        return system.traverseSystem(predicate, sum, 0);
    }

    private static int part2(Node system) {
        int systemSize = system.getDirSize();
        Predicate<Node> predicate = (node) -> node.getSize() >= 30000000 - (70000000 - systemSize);
        BiFunction<Integer, Integer, Integer> min = (x, y) -> Math.min(x, y);
        return system.traverseSystem(predicate, min, Integer.MAX_VALUE);
    }
    
    @Override
    protected
    void solve(BufferedReader reader) throws IOException {
        Node system = loadOutput(reader);
        // system.displayDir();
        int sum = part1(system);
        System.out.println("Part 1: " + sum); // 1543140
        
        int deleteDirSize = part2(system);
        System.out.println("Part 2: " + deleteDirSize); // 1117448
    }
}
