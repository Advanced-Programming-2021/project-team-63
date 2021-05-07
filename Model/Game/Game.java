package Model.Game;

import java.util.*;
//import Model.Game.Card.*;
//import Model.Game.Card.MonsterCard.*;

public class Game {
    private int id;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player inactivePlayer;
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
        setActivePlayer(getRandomPlayer());
        setPhase(Phase.BATTLE_PHASE);
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setInactivePlayer(Player inactivePlayer) {
        this.inactivePlayer = inactivePlayer;
    }

    public Player getInactivePlayer() {
        return inactivePlayer;
    }

    public void nextTurn(){
        Player tmp = getActivePlayer() ;
        setActivePlayer(getInactivePlayer());
        setInactivePlayer(tmp);
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
                setPhase(Phase.BATTLE_PHASE);
                break;
            case BATTLE_PHASE:
                setPhase(Phase.MAIN_PHASE_2);
                break;
            case MAIN_PHASE_2:
                setPhase(Phase.END_PHASE);
                break;
            case END_PHASE:
                setPhase(Phase.DRAW_PHASE);
                nextTurn();
                break;
            default:
                setPhase(Phase.DRAW_PHASE);
                break;
        }
    }

    

    public static Game getGameByID(int id){
        for(Game game : games){
            if(game.getId()==id) return game;
        }
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public Player getRandomPlayer(){
        Random rand = new Random();
        int randomPlayerNumber = rand.nextInt(2)+1;
        if(randomPlayerNumber==1) return player1;
        else return player2;
    }
}