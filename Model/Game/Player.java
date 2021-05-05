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

    public void draw(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(playerCards.size());
        Card randomCard = playerCards.get(randomIndex);
        playerHand.add(randomCard);
        playerCards.remove(randomIndex);
    }

    public void summon(){
        for (int index = 0; index < 5; index++) {
            if(getField().getMonsterZone()[index] == null) getField().getMonsterZone()[index] = selectedCard;
        }
        playerHand.remove(selectedCard);
    }
}