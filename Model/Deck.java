package Model;

import java.util.*;
import Model.Game.Card.*;

public class Deck {
    public static ArrayList<Deck> decks;
    private String name;
    private ArrayList<Card> cards;
    
    static{
        decks = new ArrayList<Deck>();
    }

    public Deck(String name){
        setName(name);
        decks.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public static Deck getDeckByName(String name){
        for(Deck deck : decks){
            if(deck.getName().equals(name)) return deck;
        }
        return null;
    }

    public static ArrayList<Deck> getDecks() {
        return decks;
    }
}
