package Model.JsonObject;

import java.util.ArrayList;
import Model.Deck;

public class AccountJson {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int money;
    private ArrayList<CardJson> purchasedCards;
    private ArrayList<DeckJson> decks;
    private Deck activeDeck;

}
