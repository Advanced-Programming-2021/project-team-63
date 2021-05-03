package Model.Game;

import java.util.*;
import Model.Game.Card.*;
import Model.*;

public class Field {
    private Card monsterZone[] = new Card[5];
    private Card trapSpellZone[] = new Card[5];
    private ArrayList<Card> graveyard;
    private Deck deck;
    private Card fieldSpellZone;

    public Card[] getMonsterZone() {
        return monsterZone;
    }
}
