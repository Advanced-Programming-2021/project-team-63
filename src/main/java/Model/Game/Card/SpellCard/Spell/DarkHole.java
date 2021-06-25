package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public class DarkHole implements Spell{
    public void activate(Game game){
        for (int i = 0; i < 5; i++) {
            game.getPlayer1().getField().killMonsterCard(game.getPlayer1().getField().getMonsterZone()[i], game); 
            game.getPlayer2().getField().killMonsterCard(game.getPlayer2().getField().getMonsterZone()[i], game); 
        }
    }
    public void activate(Game game,String CardName){}
}
