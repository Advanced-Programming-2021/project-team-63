package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.*;
import Model.Game.Card.SpellCard.*;

public class Terraforming {
    public void activate(Game game){}
    public void activate(Game game,String cardName){
        SpellCard spellCard = new SpellCard();
        for(Card card : game.getActivePlayer().getMainDeck()){
            if(card.getName().equals(cardName) && card.getCategory().equals(Category.SPELL)) spellCard = (SpellCard)card;
        }
        game.getActivePlayer().getMainDeck().remove(spellCard);
        game.getActivePlayer().addToHand(spellCard);
    }
}
