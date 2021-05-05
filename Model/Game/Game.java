package Model.Game;

import java.util.*;
import Model.Game.Card.*;
import Model.Game.Card.MonsterCard.*;

public class Game {
    private int id;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player nextPlayer;
    private Phase phase;
    private static int counter;
    private static ArrayList<Game> games;

    static{
        counter = 1;
        games = new ArrayList<Game>();
    }

    public Game(Player player1 , Player player2){
        setId(counter);
        setPlayer1(player1);
        setPlayer2(player2);
        setCurrentPlayer(player1);
        setPhase(phase);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public void nextPhase(){
        switch (getPhase()) {
            case DRAW_PHASE:
                setPhase(Phase.STANDBY_PHASE);
                break;
            case STANDBY_PHASE:
                setPhase(Phase.MAIN_PHASE_1);
                break;
            case MAIN_PHASE_1:
                setPhase(Phase.MAIN_PHASE_2);
                break;
            case BATTLE_PHASE:
                setPhase(Phase.MAIN_PHASE_2);
                break;
            case MAIN_PHASE_2:
                setPhase(Phase.END_PHASE);
                break;
            case END_PHASE:
                setPhase(Phase.DRAW_PHASE);
                break;
            default:
                setPhase(Phase.DRAW_PHASE);
                break;
        }
    }

    public void attack(Card opponentCard){
        MonsterCard attackerCard = (MonsterCard)currentPlayer.getSelectedCard();
        MonsterCard targetCard = (MonsterCard)opponentCard;
        int powerDiff = 0;
        if(targetCard.getMode().equals(Mode.ATTACK)){
            powerDiff = attackerCard.getAtk() - targetCard.getAtk();
            if(powerDiff>0){
                nextPlayer.getField().killMonsterCard(targetCard);
                nextPlayer.decreaseLp(powerDiff);
            }
            else if(powerDiff<0){
                currentPlayer.getField().killMonsterCard(attackerCard);
                currentPlayer.decreaseLp(-powerDiff);
            }
            else{
                currentPlayer.getField().killMonsterCard(attackerCard);
                nextPlayer.getField().killMonsterCard(targetCard);
            }
        }
        else{
            powerDiff = attackerCard.getAtk() - targetCard.getDef();
            if(powerDiff>0){
                nextPlayer.getField().killMonsterCard(targetCard);
            }
            else if(powerDiff<0){
                currentPlayer.decreaseLp(-powerDiff);
            }
            else;
        }
    }

    public static Game getGameByID(int id){
        for(Game game : games){
            if(game.getId()==id) return game;
        }
        return null;
    }
}