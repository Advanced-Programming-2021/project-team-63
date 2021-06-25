package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public class HarpiesFeatherDuster implements Spell{
    public void activate(Game game){
        for (int i = 0; i < 5; i++) {
            game.getInactivePlayer().getField().killSpellCard(game.getInactivePlayer().getField().getSpellZone()[i]); 
        }
    }
    public void activate(Game game,String CardName){}
}
