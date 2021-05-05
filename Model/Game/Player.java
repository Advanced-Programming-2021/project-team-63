package Model.Game;

import java.util.*;
import Model.*;
import Model.Game.Card.Card;

public class Player {
    private String playerName;
    private Field field;
    private int lp;
    private Deck deck;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> playerHand;
    private Card selectedCard;

    public Player(Account account){
        
    }

    public void addCardFromDeckToHand(){
        Card TopCard = getCardTopDeckAndRemoveIt();
        playerHand.add(TopCard);
    }

    public Card getCardTopDeckAndRemoveIt(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(playerCards.size());
        Card randomCard = playerCards.get(randomIndex);
        playerCards.remove(randomIndex);
        return randomCard;
    }

    public void summon(Card selectedCard){
        Card freeCell =  getFreeCellFromMonsterZone();
        freeCell = selectedCard;
        playerHand.remove(selectedCard);
    }

    public void setSpellAndTrap(Card selectedCard){
        Card freeCell =  getFreeCellFromTrapSpellZone();
        freeCell = selectedCard;
        playerHand.remove(selectedCard);
    }

    public Card getFreeCellFromTrapSpellZone(){
        for (int index = 0; index < 5; index++) {
            if(getField().getTrapSpellZone()[index] == null) 
                return getField().getMonsterZone()[index] ;
        }
        return null;
    }

    public Card getFreeCellFromMonsterZone(){
        for (int index = 0; index < 5; index++) {
            if(getField().getMonsterZone()[index] == null) 
                return getField().getMonsterZone()[index] ;
        }
        return null;
    }

    public ArrayList<Card> getPlayerHand(){
        return this.playerHand;
    }
  
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getLp() {
        return lp;
    }

    public void decreaseLp(int amount){
        this.lp -= amount;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }
}