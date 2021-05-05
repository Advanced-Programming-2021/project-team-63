package Model.Game;

import java.util.*;
import Model.Game.Card.*;
import Model.Game.Card.MonsterCard.*;

public class Field {
    private MonsterCard monsterZone[] = new MonsterCard[5];
    private Card trapSpellZone[] = new Card[5];
    private ArrayList<Card> graveyard;
    private Card fieldSpellZone;
    private Card fieldZone;

    public Card[] getMonsterZone() {
        return monsterZone;
    }

    public Card[] getTrapSpellZone() {
        return trapSpellZone;
    }

    public Card getFieldZone(){
        return this.fieldZone;
    }

    public ArrayList<Card> getGraveyard(){
        return graveyard;
    }

    public void killMonsterCard(MonsterCard card){
        removeFromMonsterZone(card);
        sendToGraveyard(card);
    }

    public void sendToGraveyard(Card card){
        graveyard.add(card);
    }

    public void removeFromMonsterZone(MonsterCard card){
        for (int index = 0; index < 5; index++) {
            if(monsterZone[index]==card) monsterZone[index] = null;
        }
    }
}


