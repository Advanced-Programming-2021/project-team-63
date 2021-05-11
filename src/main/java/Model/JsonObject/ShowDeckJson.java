package Model.JsonObject;

import java.util.ArrayList;

public class ShowDeckJson {
    private String name;
    private boolean isSideDeck;
    private ArrayList<CardGeneralInfo> monsters;
    private ArrayList<CardGeneralInfo> spellAndTraps;

    public ShowDeckJson(String name,boolean isSideDeck){
        this.name = name;
        this.isSideDeck = isSideDeck;
        monsters = new ArrayList<>();
        spellAndTraps = new ArrayList<>();
    }

    public void addToMonsters(CardGeneralInfo card){
        this.monsters.add(card);
    }

    public void addToSpellAndTraps(CardGeneralInfo card){
        this.spellAndTraps.add(card);
    }
}
