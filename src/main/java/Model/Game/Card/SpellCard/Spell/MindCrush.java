package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.*;

public class MindCrush implements Spell{
    public void activate(Game game){}
    public void activate(Game game,String CardName){
        boolean hasCard = false;
        for(Card card : game.getInactivePlayer().getHand()){
            if(card.getName().equals(CardName)){
                game.getInactivePlayer().getHand().remove(card);
                game.getInactivePlayer().getField().getGraveyard().add(card);
                hasCard = true;
            }
        }
        if(!hasCard){
            game.getActivePlayer().getField().getGraveyard().add(game.getActivePlayer().getRandomCard());
        }
    }
}
