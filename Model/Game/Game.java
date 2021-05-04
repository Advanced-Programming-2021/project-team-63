package Model.Game;

import java.util.*;
import Model.Account;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Phase phase;
    private Card selectedCard ;

    public Game(Account player1 , Account Player2){
        
        phase = phase.BATTLE_PHASE;
    
    }
    public void nextPhase() {
    }
    public void addCardFromDeckToHand() {
    }


}
