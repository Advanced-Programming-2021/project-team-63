package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.*;
import Model.Game.Card.MonsterCard.*;

public class MonsterReborn implements Spell{
    public void activate(Game game){}
    public void activate(Game game,String cardName){
        MonsterCard monsterCard = new MonsterCard();
        for(Card card : game.getActivePlayer().getField().getGraveyard()){
            if(card.getName().equals(cardName) && card.getCategory().equals(Category.MONSTER)) monsterCard = (MonsterCard)card;
        }
        game.getActivePlayer().getField().getGraveyard().remove(monsterCard);
        game.getActivePlayer().getField().addToMonsterZone(monsterCard);
    }
}
