package Model.JsonObject;

import Model.Game.Card.MonsterCard.Mode;
import Model.Game.Card.Status;

public class AttackInfo {
    private final String targetMonsterName;
    private final int targetAtk;
    private final int targetDef;
    private final Status targetStatus;
    private final Mode targetMode;
    private final int attakerAtk;

    public AttackInfo(String targetMonsterName, int targetAtk, int targetDef, Status targetStatus, Mode targetMode, int attakerAtk) {
        this.targetMonsterName = targetMonsterName;
        this.targetAtk = targetAtk;
        this.targetDef = targetDef;
        this.targetStatus = targetStatus;
        this.targetMode = targetMode;
        this.attakerAtk = attakerAtk;
    }
}
