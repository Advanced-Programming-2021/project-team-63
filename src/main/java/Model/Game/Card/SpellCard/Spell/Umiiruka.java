package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.MonsterCard.*;

public class Umiiruka implements Spell{
    public void activate(Game game) {
        MonsterCard monsterCard = new MonsterCard();
        for (int i = 0; i < 5; i++) {
            monsterCard = game.getPlayer1().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.AQUA) || monsterCard.getTypes().contains(Type.SEA_SERPENT)){
                monsterCard.increaseAtk(500);
                monsterCard.decreaseDef(400);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.AQUA) || monsterCard.getTypes().contains(Type.SEA_SERPENT)){
                monsterCard.increaseAtk(500);
                monsterCard.decreaseDef(400);
            }
        }    
    }
    
    public void activate(Game game, String cardName){}
    
    public void deactivate(Game game){
        MonsterCard monsterCard = new MonsterCard();
        for (int i = 0; i < 5; i++) {
            monsterCard = game.getPlayer1().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.AQUA) || monsterCard.getTypes().contains(Type.SEA_SERPENT)){
                monsterCard.decreaseAtk(500);
                monsterCard.increaseDef(400);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.AQUA) || monsterCard.getTypes().contains(Type.SEA_SERPENT)){
                monsterCard.decreaseAtk(500);
                monsterCard.increaseDef(400);
            }
        }    
    }
}
