package src.Day8;

import java.util.function.BiFunction;

public class Forrest {

    public int[][] trees;
    public int x;
    public int y;
    public int visibleTrees;
    public int maxScenicScore;

    public Forrest(int[][] trees) {
        this.trees = trees;
        this.x = 0;
        this.y = 0;
        this.visibleTrees = 0;
        this.maxScenicScore = Integer.MIN_VALUE;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < this.trees.length && y >= 0 && y < this.trees[0].length;
    }

    private boolean isTreeVisible() {

        BiFunction<Boolean, Boolean, Boolean> traverseAction = (visible, isTallerNeighbor) -> {
            return !isTallerNeighbor;
        };

        BiFunction<Boolean, Boolean, Boolean> accumulatorAction = (totalVisible, visible) -> {
            return totalVisible || visible;
        };
       
        return this.traverseNeighborTrees(false, true, traverseAction, accumulatorAction);
    }

    private int visibleTreeScore() {

        BiFunction<Integer, Boolean, Integer> traverseAction = (score, isTallerNeighbor) -> {
            return ++score;
        };

        BiFunction<Integer, Integer, Integer> accumulatorAction = (total, score) -> {
            return total *= score;
        };
       
        return this.traverseNeighborTrees(1, 0, traverseAction, accumulatorAction);
    }

    private <T> T traverseNeighborTrees(T startingTotal, T startingValue, BiFunction<T, Boolean, T> traverseAction, BiFunction<T, T, T> accumulatorAction) {
        Direction[] directions = {
            new Direction(-1, 0), // Up
            new Direction(1, 0), // Down
            new Direction(0, -1), // Right
            new Direction(0, 1), // Left
        };

        int tree = this.trees[this.x][this.y];
        T total = startingTotal;

        for (Direction direction : directions) {
            T value = startingValue;

            int x = this.x + direction.x;
            int y = this.y + direction.y;
            while (this.isInBounds(x, y)) {
                boolean isTallerNeighbor = this.trees[x][y] >= tree;
                value = traverseAction.apply(value, isTallerNeighbor);
                if (isTallerNeighbor) {
                    break;
                }
                x += direction.x;
                y += direction.y;          
            }
            total = accumulatorAction.apply(total, value);
        }
        return total;
    }

    public void explore() {
        this.visibleTrees = 0;
        this.maxScenicScore = Integer.MIN_VALUE;
        for (int i = 0; i < this.trees.length; i++) {
            for (int j = 0; j < this.trees[0].length; j++) {
                this.x = i;
                this.y = j;

                if (this.isTreeVisible()) {
                    this.visibleTrees++;
                    int score = this.visibleTreeScore();
                    this.maxScenicScore = Math.max(maxScenicScore, score);
                }
            }
        }
    }
}
