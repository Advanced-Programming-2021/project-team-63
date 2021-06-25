package Model.Game.Card.SpellCard.Spell;

import Model.Game.Game;
import Model.Game.Card.MonsterCard.MonsterCard;
import Model.Game.Card.MonsterCard.Type;

public class Yami implements Spell{
    public void activate(Game game) {
        MonsterCard monsterCard = new MonsterCard();
        for (int i = 0; i < 5; i++) {
            monsterCard = game.getPlayer1().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.FAIRY)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
            else if(monsterCard.getTypes().contains(Type.FIEND) || monsterCard.getTypes().contains(Type.SPELL_CASTER)){
                monsterCard.increaseAtk(200);
                monsterCard.increaseDef(200);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.FAIRY)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
            else if(monsterCard.getTypes().contains(Type.FIEND) || monsterCard.getTypes().contains(Type.SPELL_CASTER)){
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
            if(monsterCard.getTypes().contains(Type.FAIRY)){
                monsterCard.increaseAtk(200);
                monsterCard.increaseDef(200);
            }
            else if(monsterCard.getTypes().contains(Type.FIEND) || monsterCard.getTypes().contains(Type.SPELL_CASTER)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
            monsterCard = game.getPlayer2().getField().getMonsterZone()[i];
            if(monsterCard.getTypes().contains(Type.FAIRY)){
                monsterCard.increaseAtk(200);
                monsterCard.increaseDef(200);
            }
            else if(monsterCard.getTypes().contains(Type.FIEND) || monsterCard.getTypes().contains(Type.SPELL_CASTER)){
                monsterCard.decreaseAtk(200);
                monsterCard.decreaseDef(200);
            }
        }    
    }
}
