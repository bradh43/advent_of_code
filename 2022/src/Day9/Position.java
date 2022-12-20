package src.Day9;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isTouching(Position other) {
        return Math.abs(other.x - this.x) <= 1 && Math.abs(other.y - this.y) <= 1;
    }

    public Position apply(Position position) {
        return new Position(this.x + position.x, this.y + position.y);
    }
    
    public Position moveTowards(Position head) {
        int x = this.x + Integer.signum(head.x - this.x);
        int y = this.y + Integer.signum(head.y - this.y);

        return new Position(x, y);
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }

}
