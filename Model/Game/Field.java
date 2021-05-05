package Model.Game;

import java.util.*;
import Model.Game.Card.*;
import Model.Game.Card.SpellCard.*;
import Model.Game.Card.MonsterCard.*;

public class Field {
    private MonsterCard monsterZone[] = new MonsterCard[5];
    private SpellCard trapSpellZone[] = new SpellCard[5];
    private ArrayList<Card> graveyard;
    private Card fieldSpellZone;

    public Card[] getMonsterZone() {
        return monsterZone;
    }

    public void sendToGraveyard(Card card){
        graveyard.add(card);
    }

    public void removeFromMonsterZone(MonsterCard card){
        for (int index = 0; index < 5; index++) {
            if(monsterZone[index]==card) monsterZone[index] = null;
        }
    }

    public void killMonsterCard(MonsterCard card){
        removeFromMonsterZone(card);
        sendToGraveyard(card);
    }
}


