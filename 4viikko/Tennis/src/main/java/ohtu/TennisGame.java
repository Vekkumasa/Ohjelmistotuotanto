package ohtu;

public class TennisGame {
    
    private int player_score1 = 0;
    private int player_score2 = 0;
    private String player1Name;
    private String player2Name;
    private String score;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.score = "";
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            player_score1 += 1;
        else
            player_score2 += 1;
    }
    
    public void tasapeli(int player_score) {
        switch (player_score)   {
                case 0:
                        score = "Love-All";
                    break;
                case 1:
                        score = "Fifteen-All";
                    break;
                case 2:
                        score = "Thirty-All";
                    break;
                case 3:
                        score = "Forty-All";
                    break;
                default:
                        score = "Deuce";
                    break;      
            }
    }
    
    public void otteluPallo() {
        int erotus = player_score1 - player_score2;
        
            if (erotus == 1) {
                score = "Advantage player1";
            } else if (erotus == -1) {
                score = "Advantage player2";
            } else if (erotus >= 2){
                score = "Win for player1";
            } else {
                score = "Win for player2";
            }
    }
    
    public String tennisPisteet(int player_score) {
        switch(player_score) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
        return score;
    }
    
    public void kierros() {
        score = tennisPisteet(player_score1);
        score += "-"; 
        score = tennisPisteet(player_score2);
    }

    public String getScore() {
        if (player_score1 == player_score2) {
            tasapeli(player_score1);
        }   else if (player_score1 >= 4 || player_score2 >= 4) {
            otteluPallo();
        } else {
            kierros();
        }
        return score;
    }
}