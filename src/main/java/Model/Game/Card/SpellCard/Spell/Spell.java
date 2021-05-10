package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public interface Spell {
    public boolean isActivated(Game game);
    public void activate(Game game);
}
