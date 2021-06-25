package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public class Raigeki implements Spell{
    public void activate(Game game){
        for (int i = 0; i < 5; i++) {
            game.getInactivePlayer().getField().killMonsterCard(game.getInactivePlayer().getField().getMonsterZone()[i],game); 
        }
    }
    public void activate(Game game,String CardName){}
}