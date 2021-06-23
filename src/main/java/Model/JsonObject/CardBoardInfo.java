package Model.JsonObject;

import Model.Game.Card.MonsterCard.Attribute;
import Model.Game.Card.MonsterCard.Mode;
import Model.Game.Card.Status;

public class CardBoardInfo {
    private Mode mode;
    private Status status;

    public CardBoardInfo(Mode mode, Status status) {
        this.mode = mode;
        this.status = status;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
