package src.Day9;

public class Vector {

    public int magnitude;
    public Position direction;

    public Vector(char direction, int magnitude) {
        this.magnitude = magnitude;

        switch (direction) {
            case 'U':
                this.direction = new Position(-1 * this.magnitude, 0);
                break;
            case 'D':
                this.direction = new Position(this.magnitude, 0);
                break;
            case 'L':
                this.direction = new Position(0, -1 * this.magnitude);
                break;
            case 'R':
                this.direction = new Position(0, this.magnitude);
                break;
        }

    }

    @Override
    public String toString() {
        return "(" + this.direction.x + "," + this.direction.y + ") M=" + this.magnitude;
    }
}
