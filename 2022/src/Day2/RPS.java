package src.Day2;

public class RPS {
    // 1 for Rock, 2 for Paper, and 3 for Scissors
    public int opponent;
    public int player;
    public int goal;

    public RPS(String line) {
        // A for Rock, B for Paper, and C for Scissors (convert to 1, 2, 3 respectively)
        this.opponent = line.charAt(0) - 'A' + 1;
        // X for Rock, Y for Paper, and Z for Scissors (convert to 1, 2, 3 respectively)
        this.player = line.charAt(2) - 'X' + 1;
        // X means lose, Y means draw, and Z means win (convert to 0, 3, 6 respectively)
        this.goal = (line.charAt(2) - 'X') * 3;
    }

    // 0 if you lost, 3 if it was a draw, and 6 if you won
    public int score() {
        int outcome = 0;
        if (this.opponent == this.player) {
            outcome = 3;
        } else if ((this.opponent % 3) + 1 == this.player) {
            outcome = 6;
        }
        return outcome + this.player;
    }

    public int score2() {
        // Tie do same move
        int move = this.opponent;
        if (this.goal == 0) {
            // Lose
            move = ((this.opponent + 1) % 3) + 1;
        } else if (this.goal == 6) {
            // Win
            move = (this.opponent % 3) + 1;
        }
        return this.goal + move;
    }
}
