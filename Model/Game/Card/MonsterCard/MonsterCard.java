package Model.Game.Card.MonsterCard;

import java.util.*;
import Model.Game.Card.*;

public class MonsterCard extends Card{
    private int level;
    private int atk;
    private int def;
    private Attribute attribute;
    private Mode mode;
    private ArrayList<Type> types;
    private MonsterCategory monsterCategory;

    public MonsterCard Construct(MonsterCategory monsterCategory){
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

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }
}
