package simple;

/**
 * Would be a record.
 */
public class PlayerScore {

    private final String name;
    private int score;

    public PlayerScore(String name) {
        this.name = name;
        score = 0;
    }

    private PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String name() {
        return name;
    }

    public boolean isNamed(String name) {
        return this.name.equals(name);
    }

    public void onWonPoint() {
        int pointsWon = score < 30 ? 15 : 10;
        score += pointsWon;
    }

    @Override
    public String toString() {
        return name +": " + score;
    }

    public int score() {
        return score;
    }
}
