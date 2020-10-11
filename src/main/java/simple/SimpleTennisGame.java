package simple;

import tennis.TennisGame;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleTennisGame implements TennisGame {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private PlayerScore p1;
    private PlayerScore p2;

    private static final Map<Integer, String> POINT_NAMES = new HashMap<>() {{
        put(0, "Love");
        put(15, "Fifteen");
        put(30, "Thirty");
        put(40, "Forty");
    }};

    /**
     * I would have it a list if I would use record.
     */
    private static Map<Predicate<SimpleTennisGame>, Function<SimpleTennisGame, String>> SCORE_RULES = new LinkedHashMap<>() {
        {
            put(g -> g.isEven() && g.bothScores() == 0, g -> "Love-All");
            put(g -> g.isEven() && g.bothScores() < 40, g -> POINT_NAMES.get(g.bothScores()) + "-All");
            put(g -> g.isEven(), g -> "Deuce");
            put(g -> g.leading().score() > 40 && g.difference() > 10, g -> "Win for " + g.leading().name());
            put(g -> g.leading().score() > 40, g -> "Advantage " + g.leading().name());
            put(g -> true, g -> POINT_NAMES.get(g.p1.score()) + "-" + POINT_NAMES.get(g.p2.score()));
        }
    };

    public SimpleTennisGame(String player1Name, String player2Name) {
        p1 = new PlayerScore(player1Name);
        p2 = new PlayerScore(player2Name);
    }

    public void wonPoint(String playerName) {
        (p1.isNamed(playerName) ? p1 : p2).onWonPoint();
        System.out.println(p1 + " - " + p2);
    }

    private boolean isEven() {
        return p1.score() == p2.score();
    }

    private int difference() {
        return Math.abs(p1.score() - p2.score());
    }

    private PlayerScore leading() {
        return p1.score() > p2.score() ? p1 : p2;
    }

    private int bothScores() {
        if (!isEven()) throw new RuntimeException("Score not equal");
        return p1.score();
    }

    public String getScore() {
        return SCORE_RULES.entrySet().stream()
                .filter(e -> e.getKey().test(this))
                .findFirst()
                .map(e -> e.getValue().apply(this))
                .orElseThrow(() -> new RuntimeException("Unsupported score! " + p1 + " " + p2));
    }

}
