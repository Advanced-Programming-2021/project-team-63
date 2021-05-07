package Model.Game.Card.MonsterCard.Effect;

import Model.Game.*;

public interface Effect {
    public boolean isActivated(Game game);
    public void activate(Game game);
}
