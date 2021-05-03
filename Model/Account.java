package Model;

import java.util.*;
import Model.Game.Card.*;

public class Account{
    public static ArrayList<Account> accounts;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int money;
    private ArrayList<Card> purchasedCards;
    private ArrayList<Deck> decks;
    private Deck mainDeck;
    private Deck sideDeck;

    static{
        accounts = new ArrayList<Account>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score){
        this.score += score;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
    
    public void increaseMoney(int money){
        this.money += money;
    }

    public void decreaseMoney(int money){
        this.money -= money;
    }

    public void addToPurchasedCard(Card card){
        purchasedCards.add(card);
    }

    public ArrayList<Card> getPurchasedCards() {
        return purchasedCards;
    }

    public boolean hasCard(Card card){
        if(purchasedCards.contains(card)) return true;
        return false;
    }

    public void addToDecks(Deck deck){
        decks.add(deck);
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public boolean hasDeck(Deck deck){
        if(decks.contains(deck)) return true;
        return false;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }
    
    public void setSideDeck(Deck sideDeck) {
        this.sideDeck = sideDeck;
    }

    public Deck getSideDeck() {
        return sideDeck;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static Account getAccountByUsername(String username){
        for(Account account : accounts){
            if(account.getUsername().equals(username)) return account;
        }
        return null;
    }
}