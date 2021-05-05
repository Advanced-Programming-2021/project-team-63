package Model;

import java.util.*;
import Model.Game.Card.*;

public class Deck {
    private String name;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> sideDeck;

    public Deck(String name){
        setName(name);
        mainDeck = new ArrayList<Card>();
        sideDeck = new ArrayList<Card>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCardToMainDeck(Card card){
        mainDeck.add(card);
    }

    public void removeCardFromMainDeck(Card card){
        mainDeck.remove(card);
    }

    public ArrayList<Card> getMainDeck() {
        return mainDeck;
    }

    public int getMainDeckSize(){
        return mainDeck.size();
    }

    public void addCardToSideDeck(Card card){
        sideDeck.add(card);
    }

    public void removeCardFromSideDeck(Card card){
        sideDeck.remove(card);
    }

    public ArrayList<Card> getSideDeck() {
        return sideDeck;
    }

    public int getSideDeckSize(){
        return sideDeck.size();
    }

    public boolean isValid(){
        if(mainDeck.size() >= 40) return true;
        else return false;
    }

    public int cardCount(Card card){
        int count = 0;
        for(Card c : mainDeck){
            if(c.getName().equals(card.getName())) count++;
        }
        for(Card c : sideDeck){
            if(c.getName().equals(card.getName())) count++;
        }
        return count;
    }
}