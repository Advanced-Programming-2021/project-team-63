package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.MonsterCard.*;

public class SwordOfDarkDestruction implements Spell {
    private boolean wasActivated = false;
    private MonsterCard bindedCard;

    public void activate(Game game){}
    public void activate(Game game,String cardName){
        if(!wasActivated){
            for(MonsterCard monsterCard : game.getActivePlayer().getField().getMonsterZone()){
                if(monsterCard.getName().equals(cardName) && monsterCard.getAttribute().equals(Attribute.DARK)) bindedCard = monsterCard;
            }
            bindedCard.increaseAtk(400);
            bindedCard.decreaseDef(200);
            wasActivated = true;
        }
    }
    public void deactivate(Game game){
        bindedCard.decreaseAtk(400);
        bindedCard.increaseDef(200);
    }
}
