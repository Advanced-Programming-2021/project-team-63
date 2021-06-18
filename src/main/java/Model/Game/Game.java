package Model.Game;

import java.util.*;
//import Model.Game.Card.*;
//import Model.Game.Card.MonsterCard.*;
import Model.Game.Card.GameLogType;
import Model.Game.Card.MonsterCard.Mode;
import org.json.JSONObject;
import org.json.JSONArray;


public class Game {
    private int id;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player inactivePlayer;
    private Phase phase;
    private int rounds;
    private static int counter;
    private static ArrayList<Game> games;
    private ArrayList<String> gameLog;

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
        gameLog = new ArrayList<>();
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

    public ArrayList<String> getGameLog(){
        return this.gameLog;
    }



    public void addToGameLog(String log){
        gameLog.add(log);
        //ToDo: check effects
    }

    public void addSelectCardLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SELECT_CARD);
        jsonObject.put("cardIdentity", cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addDeselectCardLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.DESELECT_CARD);
        jsonObject.put("cardIdentity", cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addNextPhaseLog(Phase nowPhase) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.NEXT_PHASE);
        jsonObject.put("newPhase", getPhase());
        addToGameLog(jsonObject.toString());
    }

    public void addAddCardFromDeckToHandLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.ADD_CARD_TO_HAND);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addSummonMonsterLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SUMMON_MONSTER);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addSummonMonsterWith1Tribute(int cardIdentity, int victimIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SUMMON_MONSTER_WITH_1TRIBUTE);
        jsonObject.put("cardIdentity",cardIdentity);
        jsonObject.put("victimIdentity",victimIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addSummonMonsterWith2Tributes(int cardIdentity, int victim1Identity, int victim2Identity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SUMMON_MONSTER_WITH_2TRIBUTES);
        jsonObject.put("cardIdentity",cardIdentity);
        jsonObject.put("victim1Identity",victim1Identity);
        jsonObject.put("victim2Identity",victim2Identity);
        addToGameLog(jsonObject.toString());
    }

    public void addSetMonsterLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SET_MONSTER);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addChangeMonsterModeLog(int cardIdentity, Mode newMode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.CHANGE_MONSTER_MODE);
        jsonObject.put("cardIdentity",cardIdentity);
        jsonObject.put("newMode",newMode);
        addToGameLog(jsonObject.toString());
    }

    public void addFlipSummonMonsterLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.FLIP_SUMMON_MONSTER);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addAttackLog(int cardIdentity, int targetIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.ATTACK);
        jsonObject.put("cardIdentity",cardIdentity);
        jsonObject.put("targetIdentity",targetIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addDeathMonster(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.DEATH_MONSTER);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addDirectAttackLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.DIRECT_ATTACK);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addActiveFieldLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.ACTIVE_EFFECT_FIELD);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addActiveSpellLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.ACTIVE_EFFECT_SPELL);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }

    public void addSetSpellLog(int cardIdentity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SET_SPELL);
        jsonObject.put("cardIdentity",cardIdentity);
        addToGameLog(jsonObject.toString());
    }


}