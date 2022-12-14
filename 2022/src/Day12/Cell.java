package src.Day12;

public class Cell {

    public int elevation;
    public boolean isStart;
    public boolean isEnd;
    public boolean visited;

    public Cell(char elevation) {
        this.isStart = false;
        this.isEnd = false;
        if (elevation == 'S') {
            this.isStart = true;
            elevation = 'a';
        } else if (elevation == 'E') {
            this.isEnd = true;
            elevation = 'z';
        }
        this.visited = false;
        this.elevation = elevation - 'a';
    }

    public boolean canVisit(Cell cell) {
        return cell.elevation - this.elevation <= 1;
    }
}
