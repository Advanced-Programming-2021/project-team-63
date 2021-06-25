package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public class PotOfGreed implements Spell{
    public void activate(Game game){
        game.getActivePlayer().draw();
        game.getActivePlayer().draw();
    }
    public void activate(Game game,String cardName){

    }
}
