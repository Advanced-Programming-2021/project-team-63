package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;
import Model.Game.Card.Status;

public class SwordsOfRevealingLight implements Spell{
    public void activate(Game game){
        boolean hasSet = false;
        for (int i = 0; i < 5; i++) {
            if(game.getInactivePlayer().getField().getMonsterZone()[i].getStatus().equals(Status.SET)) hasSet = true;
        }
        if(hasSet){
            for (int i = 0; i < 5; i++) {
                game.getInactivePlayer().getField().getMonsterZone()[i].setStatus(Status.SET);
            }
        }
    }
    public void activate(Game game,String cardName){}
}
