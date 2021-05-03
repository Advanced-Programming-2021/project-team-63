package Model.Game.Card.MonsterCard;

import java.util.*;
import Model.Game.Card.*;

public abstract class MonsetrCard extends Card{
    private int level;
    private int atk;
    private int def;
    private ArrayList<Type> types;
    private Attribute attribute;
    private MonsterCategory monsterCategory;

    public static MonsetrCard Construct(MonsterCategory monsterCategory){
        return null;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getAtk() {
        return atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getDef() {
        return def;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
