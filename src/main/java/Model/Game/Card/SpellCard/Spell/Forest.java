package Model.Game.Card.SpellCard.Spell;

import Model.Game.Game;
import Model.Game.Card.MonsterCard.*;

public class Forest implements Spell{
    public void activate(Game game) {
        MonsterCard monsterCard = new MonsterCard();
        for (int i = 0; i < 5; i++) {
            monsterCard = game.getPlayer1().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.INSECT) || monsterCard.getTypes().contains(Type.BEAST) || monsterCard.getTypes().contains(Type.BEAST_WARRIOR)){
                monsterCard.increaseAtk(200);
                monsterCard.increaseDef(200);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.INSECT) || monsterCard.getTypes().contains(Type.BEAST) || monsterCard.getTypes().contains(Type.BEAST_WARRIOR)){
                monsterCard.increaseAtk(200);
                monsterCard.increaseDef(200);
            }
        }    
    }
    
    public void activate(Game game, String cardName){}
    
    public void deactivate(Game game){
        MonsterCard monsterCard = new MonsterCard();
        for (int i = 0; i < 5; i++) {
            monsterCard = game.getPlayer1().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.INSECT) || monsterCard.getTypes().contains(Type.BEAST) || monsterCard.getTypes().contains(Type.BEAST_WARRIOR)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.INSECT) || monsterCard.getTypes().contains(Type.BEAST) || monsterCard.getTypes().contains(Type.BEAST_WARRIOR)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
        }    
    }
}
