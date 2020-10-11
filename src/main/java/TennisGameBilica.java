import tennis.TennisGame;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class TennisGameBilica implements TennisGame {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private PlayerScore player1Score;
    private PlayerScore player2Score;

    private static final Map<BiPredicate<Score, Score>, BiFunction<PlayerScore, PlayerScore, String>> SCORE_PRESENTERS = new LinkedHashMap<>();
    {
        SCORE_PRESENTERS.put((s1, s2) -> s1 == Score.DEUCE && s2 == Score.DEUCE, (p1, p2) -> "Deuce");
        SCORE_PRESENTERS.put((s1, s2) -> s1 == s2, (p1, p2) -> capitalize(p1.getScore().toString()) + "-All");
        SCORE_PRESENTERS.put((s1, s2) -> s1 == Score.WIN, (p1, p2) -> "Win for " + p1.getName());
        SCORE_PRESENTERS.put((s1, s2) -> s2 == Score.WIN, (p1, p2) -> "Win for " + p2.getName());
        SCORE_PRESENTERS.put((s1, s2) -> s1 == Score.ADVANTAGE, (p1, p2) -> "Advantage " + p1.getName());
        SCORE_PRESENTERS.put((s1, s2) -> s2 == Score.ADVANTAGE, (p1, p2) -> "Advantage " + p2.getName());
        SCORE_PRESENTERS.put((s1, s2) -> true, (p1, p2) -> capitalize(p1.getScore().toString()) + "-" + capitalize(p2.getScore().toString()));
    }
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public TennisGameBilica(String player1Name, String player2Name) {
        player1Score = new PlayerScore(player1Name);
        player2Score = new PlayerScore(player2Name);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void wonPoint(String playerName) {
        PlayerScore winnerOfThePoint = getWinnerOfThePoint(playerName);
        PlayerScore newP1 = player1Score.updateScore(winnerOfThePoint, player2Score);
        PlayerScore newP2 = player2Score.updateScore(winnerOfThePoint, player1Score);
        player1Score = newP1;
        player2Score = newP2;
        System.out.println(playerName + " " + player1Score.getScore() + " " + player2Score.getScore());
    }

    private PlayerScore getWinnerOfThePoint(String playerName) {
        return player1Score.getName().equals(playerName) ? player1Score : player2Score;
    }

    public String getScore() {
        return SCORE_PRESENTERS.entrySet().stream()
                .filter(e -> e.getKey().test(player1Score.getScore(), player2Score.getScore()))
                .findFirst().get().getValue()
                .apply(player1Score, player2Score);
    }

    private String capitalize(String str) {
        return str.substring(0, 1) + str.substring(1).toLowerCase();
    }
}
