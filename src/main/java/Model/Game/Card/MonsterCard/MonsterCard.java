package Model.Game.Card.MonsterCard;

import java.util.*;
import Model.Game.*;
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

    public void addToTypes(Type type){
        types.add(type);
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setMonsterCategory(MonsterCategory monsterCategory) {
        this.monsterCategory = monsterCategory;
    }

    public MonsterCategory getMonsterCategory() {
        return monsterCategory;
    }

    public void attack(Game game, MonsterCard targetCard){
        int powerDiff = 0;
        if(targetCard.getMode().equals(Mode.ATTACK)){
            powerDiff = atk - targetCard.getAtk();
            if(powerDiff>0){
                game.getActivePlayer().getField().killMonsterCard(targetCard);
                game.getInactivePlayer().decreaseLp(powerDiff);
            }
            else if(powerDiff<0){
                game.getActivePlayer().getField().killMonsterCard(this);
                game.getActivePlayer().decreaseLp(-powerDiff);
            }
            else{
                game.getActivePlayer().getField().killMonsterCard(this);
                game.getInactivePlayer().getField().killMonsterCard(targetCard);
            }
        }
        else{
            powerDiff = atk - targetCard.getDef();
            if(powerDiff>0){
                game.getInactivePlayer().getField().killMonsterCard(targetCard);
            }
            else if(powerDiff<0){
                game.getActivePlayer().decreaseLp(-powerDiff);
            }
            else;
        }
    }

    public void directAttack(Game game){
        game.getInactivePlayer().decreaseLp(atk);
    }
}