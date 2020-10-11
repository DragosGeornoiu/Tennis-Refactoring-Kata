import tennis.TennisGame;

/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
public class TennisGame3 implements TennisGame {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private int p2;
    private int p1;
    private String p1N;
    private String p2N;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String getScore() {
        String s;
        if ((p1 < 4) && (p2 < 4) && !((p1 + p2) == 6)) {
            String[] p = new String[] { "Love", "Fifteen", "Thirty", "Forty" };
            s = p[p1];
            return (p1 == p2) ? (s + "-All") : (s + "-" + p[p2]);
        } else {
            if (p1 == p2)
                return "Deuce";
            s = (p1 > p2) ? p1N : p2N;
            return (((p1 - p2) * (p1 - p2)) == 1) ? ("Advantage " + s) : ("Win for " + s);
        }
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            this.p1 += 1;
        else
            this.p2 += 1;

    }

}
