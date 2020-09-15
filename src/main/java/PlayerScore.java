public class PlayerScore {

    private final Score score;
    private final String name;

    public PlayerScore(String name) {
        this.name = name;
        score = Score.LOVE;
    }

    private PlayerScore(String name, Score newScore) {
        this.name = name;
        this.score = newScore;
    }

    public String getName() {
        return name;
    }

    public PlayerScore updateScore(PlayerScore winnerOfPoint, PlayerScore otherPlayerScore) {
        Score newScore = Score.SCORE_SCORE_UPDATER.get(score).update(winnerOfPoint.name.equals(name), otherPlayerScore.score);
        return new PlayerScore(name, newScore);
    }

    public Score getScore() {
        return score;
    }
}
