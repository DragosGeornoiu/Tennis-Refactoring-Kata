import java.util.HashMap;
import java.util.Map;

public enum Score {
    LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE, DEUCE, WIN;

    public static final Map<Score, ScoreUpdater> SCORE_SCORE_UPDATER = new HashMap<>();

    static {
        SCORE_SCORE_UPDATER.put(LOVE, (wonByMe, adversaryScore) ->
                wonByMe ? FIFTEEN : LOVE
        );
        SCORE_SCORE_UPDATER.put(FIFTEEN, ((wonByMe, adversaryScore) -> wonByMe ? THIRTY : FIFTEEN));

        SCORE_SCORE_UPDATER.put(THIRTY, ((wonByMe, adversaryScore) -> {
            if (wonByMe)
                if (adversaryScore == FORTY) return DEUCE;
                else return FORTY;
            else return THIRTY;
        }));

        SCORE_SCORE_UPDATER.put(FORTY, ((wonByMe, adversaryScore) -> {
            if (wonByMe) return WIN;
            else if (adversaryScore == THIRTY) return DEUCE;
            else return FORTY;
        }));

        SCORE_SCORE_UPDATER.put(ADVANTAGE, ((wonByMe, adversaryScore) -> wonByMe ? WIN : DEUCE));

        SCORE_SCORE_UPDATER.put(DEUCE, ((wonByMe, adversaryScore) -> {
            if (wonByMe)
                if (adversaryScore == ADVANTAGE) return DEUCE;
                else return ADVANTAGE;
            else return DEUCE;
        }));
    }

    public interface ScoreUpdater {
        Score update(boolean wonByMe, Score adversaryScore);
    }

}
