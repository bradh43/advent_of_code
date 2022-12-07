package src.Day7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Node {

    public List<Node> children;
    public boolean isDir;
    public int size;
    public String name;

    public Node(String name) {
        this.name = name;
        this.isDir = true;
        this.children = new ArrayList<>();
    }

    public Node(String name, int size) {
        this.name = name;
        this.size = size;
        this.children = null;
    }

    public boolean isDir() {
        return this.isDir;
    }

    public void addFile(Node file) {
        children.add(file);
    }

    public int getSize() {
        if (this.isDir) {
            int totalSize = 0;
            for (Node node : children) {
                totalSize += node.getSize();
            }
            return totalSize;
        }
        return this.size;
    }

    public int traverseSystem(Predicate<Node> predicate, BiFunction<Integer, Integer, Integer> action, int startingValue) {

        int value = startingValue;

        if (predicate.test(this)) {
            value = action.apply(value, this.getSize());
        }

        for (Node child : this.children) {
            if (child.isDir()) {
                value = action.apply(value, child.traverseSystem(predicate, action, startingValue));
            }
        }

        return value;
    }

    private int getDirSizeKernel(Node node) {
        int sum = 0;
        for (Node child : node.children) {
            if (child.isDir()) {
                sum += getDirSizeKernel(child);
                continue;
            }
            sum += child.getSize();
        }
        return sum;
    }

    public int getDirSize() {
        return getDirSizeKernel(this);
    }

    private void printPadding(int padding){
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    private void displayDirKernel(Node node, int padding) {
        printPadding(padding);
        System.out.println(node.toString());
        padding += 4;
        for (Node child : node.children) {
            if (child.isDir()) {
                displayDirKernel(child, padding);
                continue;
            }
            printPadding(padding);
            System.out.println(child.toString());
        }
    }

    public void displayDir() {
        displayDirKernel(this, 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ");
        sb.append(this.name);
        sb.append(" (");
        if (isDir) {
            sb.append("dir");
        } else {
            sb.append("file, size=");
            sb.append(this.size);
        }
        sb.append(")");
        return sb.toString();
    }
}
