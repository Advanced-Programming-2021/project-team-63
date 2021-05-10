package Model.Game;

import java.util.*;
import Model.Game.Card.*;
import Model.Game.Card.SpellCard.*;
import Model.Game.Card.MonsterCard.*;

public class Field {
    private MonsterCard monsterZone[] = new MonsterCard[5];
    private SpellCard spellZone[] = new SpellCard[5];
    private ArrayList<Card> graveyard;
    private SpellCard fieldZone;

    public MonsterCard[] getMonsterZone() {
        return monsterZone;
    }

    public SpellCard[] getSpellZone() {
        return spellZone;
    }

    public void setFieldZone(SpellCard fieldZone) {
        this.fieldZone = fieldZone;
    }

    public SpellCard getFieldZone(){
        return this.fieldZone;
    }

    public ArrayList<Card> getGraveyard(){
        return graveyard;
    }

    public void addToMonsterZone(MonsterCard monsterCard){
        for (int index = 0; index < 5; index++) {
            if(monsterZone[index]==null) monsterZone[index] = monsterCard;
        }
    }

    public void addToSpellZone(SpellCard spellCard){
        for (int index = 0; index < 5; index++) {
            if(spellZone[index]==null) spellZone[index] = spellCard;
        } 
    }

    public void addToGraveyard(Card card){
        graveyard.add(card);
    }

    public void removeFromMonsterZone(MonsterCard monsterCard){
        for (int index = 0; index < 5; index++) {
            if(monsterZone[index]==monsterCard) monsterZone[index] = null;
        }
    }

    public void removeFromSpellZone(SpellCard spellCard){
        for (int index = 0; index < 5; index++) {
            if(spellZone[index]==spellCard) spellZone[index] = null;
        }
    }

    public void emptyFieldZone(){
        setFieldZone(null);
    }
    
    public void killMonsterCard(MonsterCard monsterCard){
        removeFromMonsterZone(monsterCard);
        addToGraveyard(monsterCard);
    }

    public void killSpellCard(SpellCard spellCard){
        removeFromSpellZone(spellCard);
        addToGraveyard(spellCard);
    }

    public void killFieldCard(SpellCard spellCard){
        emptyFieldZone();
        addToGraveyard(spellCard);
    }
}


