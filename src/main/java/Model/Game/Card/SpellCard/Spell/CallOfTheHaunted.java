package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.*;
import Model.Game.Card.MonsterCard.*;

public class CallOfTheHaunted implements Spell{
    private boolean wasActivated = false;
    private MonsterCard bindedCard;

    public void activate(Game game){}

    public void activate(Game game,String cardName){
        if(!wasActivated){
            for(Card card : game.getActivePlayer().getField().getGraveyard()){
                if(card.getName().equals(cardName) && card.getCategory().equals(Category.MONSTER)) bindedCard = (MonsterCard)card;
            }
            game.getActivePlayer().getField().getGraveyard().remove(bindedCard);
            game.getActivePlayer().getField().addToMonsterZone(bindedCard);
            wasActivated = true;
        }
    }
    
    public void deactivate(Game game){
        game.getInactivePlayer().getField().killMonsterCard(bindedCard, game);
    }
}
