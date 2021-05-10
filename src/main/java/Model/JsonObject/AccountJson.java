package Model.JsonObject;

import java.util.ArrayList;
import Model.Deck;

public class AccountJson {
    private String username;//Identity
    private String password;
    private String nickname;
    private int score;
    private int money;
    private ArrayList<CardJson> purchasedCards;
    private ArrayList<DeckJson> decks;
    private DeckJson activeDeck;

    public AccountJson(String username, String password, String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        this.money = 0;
        purchasedCards = new ArrayList<>();
        decks = new ArrayList<>();
        activeDeck = new DeckJson();
    }

    public String getUsername() {
        return this.username;
    }

    public String getNickname(){
        return this.nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public int getScore(){
        return this.score;
    }
}
