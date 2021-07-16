package Controller;

import java.util.ArrayList;
import Model.ApiMessage;
import Model.Game.Card.Category;
import Model.Game.Card.GameLogType;
import Model.Game.Card.MonsterCard.Mode;
import Model.Game.Card.MonsterCard.MonsterCard;
import Model.Game.Card.MonsterCard.MonsterCategory;
import Model.Game.Card.SpellCard.Icon;
import Model.Game.Card.SpellCard.SpellCard;
import Model.Game.Card.Status;
import Model.Game.CardAddress;
import Model.Game.Game;
import Model.Game.Card.Card;
import Model.Game.Phase;
import Model.Game.Player;
import Model.JsonObject.*;
import com.google.gson.Gson;
import org.json.JSONObject;

public class GameController{
    private static boolean justOneObject = false;
    private Game game ;
    private ProgramController programController;

    public GameController(ProgramController programController){
        assert !justOneObject;
        justOneObject = true;
        game = null;
    }

    public ApiMessage createGame(AccountJson player1, AccountJson player2, int rounds) throws Exception {
        Player playerOne = new Player(player1.getNickname(),cardJsonToString(player1.getActiveDeck().getMainDeck()),cardJsonToString(player1.getActiveDeck().getSideDeck()),0);
        Player playerTwo = new Player(player2.getNickname(),cardJsonToString(player2.getActiveDeck().getMainDeck()),cardJsonToString(player2.getActiveDeck().getSideDeck()),0);
        game = new Game(playerOne, playerTwo, rounds);
        return new ApiMessage(ApiMessage.successful,"duel was created successfully.");
    }

    public ApiMessage selectCard(CardAddress address, int id) throws Exception {
        Card selectedCard;
        switch (address){
            case SPELL_ZONE:
                if(5 < id)
                    return new ApiMessage(ApiMessage.error,"invalid selection");
                selectedCard = game.getActivePlayer().getField().getSpellZone()[id-1];
                break;
            case MONSTER_ZONE:
                if(game.getActivePlayer().getField().getMonsterZone().length < id)
                    return new ApiMessage(ApiMessage.error,"invalid selection");
                selectedCard = game.getActivePlayer().getField().getMonsterZone()[id-1];
                break;
            case OPPONENT_SPELL_ZONE:
                if(game.getActivePlayer().getField().getSpellZone().length < id)
                    return new ApiMessage(ApiMessage.error,"invalid selection");
                selectedCard = game.getInactivePlayer().getField().getSpellZone()[id-1];
                break;
            case FIELD_ZONE:
                assert id==0;
                selectedCard = game.getActivePlayer().getField().getFieldZone();
                break;
            case OPPONENT_FIELD_ZONE:
                assert id==0;
                selectedCard = game.getInactivePlayer().getField().getFieldZone();
                break;
            case HAND:
                var hand = game.getActivePlayer().getHand();
                if(hand.size() < id)
                    return new ApiMessage(ApiMessage.error,"invalid selection");
                selectedCard = hand.get(id - 1);
                break;
            default:
                return new ApiMessage(ApiMessage.error,"invalid selection");
        }
        if(selectedCard == null){
            return new ApiMessage(ApiMessage.error,"no card found in the given position");
        }
        game.getActivePlayer().setSelectedCard(selectedCard);
        game.addToGameLog(GameLogType.SELECT_CARD, selectedCard.hashCode());
        return new ApiMessage(ApiMessage.successful,"card selected");
    }

    public ApiMessage deselectCard() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");
        game.addToGameLog(GameLogType.DESELECT_CARD, game.getActivePlayer().getSelectedCard().hashCode());
        game.getActivePlayer().setSelectedCard(null);
        return new ApiMessage(ApiMessage.successful,"card deselected");
    }

    public ApiMessage nextPhase() throws Exception {
        game.nextPhase();
        game.addNextPhaseLog(game.getPhase());
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(game.getPhase()));
    }

    public ApiMessage addCardFromDeckToHand() throws Exception {
        var card = game.getActivePlayer().draw();
        if(card !=null) {
            game.addToGameLog(GameLogType.ADD_CARD_TO_HAND,card.hashCode());
            return new ApiMessage(ApiMessage.successful,"new card added to the hand : " + card.getName());
        }
        return  new ApiMessage(ApiMessage.error,"no card left to draw");
    }

    public ApiMessage summonMonster() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getHand().contains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t summon this card");

//        if(!game.getActivePlayer().getSelectedCard().getCategory().equals(Category.MONSTER))
//            return new ApiMessage(ApiMessage.error,"you can't summon this card");

        var selectedCard = (MonsterCard)game.getActivePlayer().getSelectedCard();

        if(selectedCard.getMonsterCategory() == MonsterCategory.RITUAL)
            return new ApiMessage(ApiMessage.error,"you can’t summon this card");

        if(game.getPhase() != Phase.MAIN_PHASE_1 && game.getPhase() != Phase.MAIN_PHASE_2)
            return new ApiMessage(ApiMessage.error,"action not allowed in this phase");

        if(game.getActivePlayer().getField().getCntFreeCellsInMonsterZone() == 0)
            return new ApiMessage(ApiMessage.error, "monster card zone is full");

        if(game.getActivePlayer().isMonsterSummon())
            return new ApiMessage(ApiMessage.error,"you already summoned on this turn");

        if(game.getActivePlayer().isMonsterSet())
            return new ApiMessage(ApiMessage.error,"you already set on this turn");

        if(selectedCard.getLevel() <= 4){
            game.getActivePlayer().summon(selectedCard);
            game.addSummonMonsterLog(selectedCard.hashCode());
            return new ApiMessage(ApiMessage.successful,("summoned successfully"));
        }

        else if(selectedCard.getLevel() <= 6){
            if(5 - game.getActivePlayer().getField().getCntFreeCellsInMonsterZone() < 1)
                return new ApiMessage(ApiMessage.error,"there are not enough cards for tribute");
            return new ApiMessage(ApiMessage.successful,"{\"tribute\":1}");
        }


        else{
            if(5 - game.getActivePlayer().getField().getCntFreeCellsInMonsterZone() < 2)
                return new ApiMessage(ApiMessage.error,"there are not enough cards for tribute");
            return new ApiMessage(ApiMessage.successful,"{\"tribute\":2}");
        }
    }

    public ApiMessage getTributeForSummonMonster(int victimMonsterCellId) throws Exception {
        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();
        var victimMonster = game.getActivePlayer().getField().getMonsterZone()[victimMonsterCellId-1];

        if(victimMonster == null)
            return new ApiMessage(ApiMessage.error,"there no monsters one this address");

        game.getActivePlayer().tributeSummon(selectedCard , victimMonster, game);
        game.addSummonMonsterWith1Tribute(selectedCard.hashCode(),victimMonster.hashCode());
        return new ApiMessage(ApiMessage.successful,"summoned successfully");
    }

    public ApiMessage getTributesForSummonMonster(int victimMonsterCellId1, int victimMonsterCellId2) throws Exception {
        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();
        var victimMonster1 = game.getActivePlayer().getField().getMonsterZone()[victimMonsterCellId1-1];
        var victimMonster2 = game.getActivePlayer().getField().getMonsterZone()[victimMonsterCellId2-1];

        if(victimMonster1 == null || victimMonster2 == null)
            return new ApiMessage(ApiMessage.error,"there is no monster on one of these addresses");

        game.getActivePlayer().tributeSummon(selectedCard , victimMonster1 , victimMonster2, game);
        game.addSummonMonsterWith2Tributes(selectedCard.hashCode(),victimMonster1.hashCode(),victimMonster2.hashCode());
        return new ApiMessage(ApiMessage.successful,"summoned successfully");
    }

    public ApiMessage setMonster() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getHand().contains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t set this card");

        if(game.getPhase() != Phase.MAIN_PHASE_1 && game.getPhase() != Phase.MAIN_PHASE_2)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        assert game.getActivePlayer().getSelectedCard().getCategory() == Category.MONSTER;

        if(game.getActivePlayer().getField().getCntFreeCellsInMonsterZone() == 0)
            return new ApiMessage(ApiMessage.error,"monster card zone is full");

        if(game.getActivePlayer().isMonsterSummon())
            return new ApiMessage(ApiMessage.error,"you already summoned on this turn");

        if(game.getActivePlayer().isMonsterSet())
            return new ApiMessage(ApiMessage.error,"you already set on this turn");

        game.getActivePlayer().setMonster((MonsterCard) game.getActivePlayer().getSelectedCard());
        game.addToGameLog(GameLogType.SET_MONSTER,game.getActivePlayer().getSelectedCard().hashCode());
        return new ApiMessage(ApiMessage.successful,"set successfully");
    }

    public ApiMessage changeMonsterMode(Mode newMode) throws Exception {

        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getField().isMonsterZoneContains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t change this card position");

        if(game.getPhase() != Phase.MAIN_PHASE_1 && game.getPhase() != Phase.MAIN_PHASE_2)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();

        if(newMode != selectedCard.getMode())
            return new ApiMessage(ApiMessage.error,"this card is already in the wanted position");

        if(selectedCard.isChangeModeInTurn())
            return new ApiMessage(ApiMessage.error,"you already changed this card position in this turn");

        game.getActivePlayer().changeMode(selectedCard, newMode);
        game.addToGameLog(GameLogType.CHANGE_MONSTER_MODE,selectedCard.hashCode());
        return new ApiMessage(ApiMessage.successful,"monster card position changed successfully");
    }

    public ApiMessage flipSummonMonster() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getField().isMonsterZoneContains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t change this card position");

        if(game.getPhase() != Phase.MAIN_PHASE_1 && game.getPhase() != Phase.MAIN_PHASE_2)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();

        if(selectedCard.getStatus() != Status.SET)
            return new ApiMessage(ApiMessage.error,"you can’t flip summon this card");

        game.getActivePlayer().flipSummon(selectedCard);
        game.addToGameLog(GameLogType.FLIP_SUMMON_MONSTER,selectedCard.hashCode());
        return new ApiMessage(ApiMessage.successful,"flip summoned successfully");
    }

    public ApiMessage attack(int targetMonsterCellId) throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getField().isMonsterZoneContains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t attack with this card");

        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();

        if(selectedCard.getMode() != Mode.ATTACK)
            return new ApiMessage(ApiMessage.error,"you can’t attack with this card");

        if(game.getPhase() != Phase.BATTLE_PHASE)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        if(selectedCard.isMonsterAttackInTurn())
            return new ApiMessage(ApiMessage.error,"this card already attacked");


        var targetMonster = game.getInactivePlayer().getField().getMonsterZone()[targetMonsterCellId-1];

        if(targetMonster == null)
            return new ApiMessage(ApiMessage.error,"there is no card to attack here");

        game.addAttackLog(selectedCard.hashCode(),targetMonster.hashCode());
        var ans = game.getActivePlayer().attack(game , selectedCard , targetMonster);
        var attemptToEndRound = isRoundOver();
        if(attemptToEndRound != null)
            return attemptToEndRound;
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(ans));
    }

    public ApiMessage directAttack() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getField().isMonsterZoneContains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t attack with this card");

        if(game.getPhase() != Phase.BATTLE_PHASE)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        var selectedCard = (MonsterCard) game.getActivePlayer().getSelectedCard();

        if(selectedCard.isMonsterAttackInTurn())
            return new ApiMessage(ApiMessage.error,"this card already attacked");

        if(game.getInactivePlayer().getField().getCntFreeCellsInMonsterZone() != 5 || selectedCard.getMode() != Mode.ATTACK)
            return new ApiMessage(ApiMessage.error,"you can’t attack the opponent directly");

        game.addToGameLog(GameLogType.DIRECT_ATTACK,selectedCard.hashCode());
        game.getActivePlayer().directAttack(game,selectedCard);
        var attemptToEndRound = isRoundOver();
        if(attemptToEndRound != null)
            return attemptToEndRound;
        return new ApiMessage(ApiMessage.successful,"{\"damage\"=" + selectedCard.getAtk() + "}");
    }

    public ApiMessage isRoundOver() throws Exception {
        Player looser;
        Player winner;
        if(game.getPlayer1().getLp() <= 0){
            looser = game.getPlayer1();
            winner = game.getPlayer2();
            return endRound(looser,winner);
        }
        if(game.getPlayer2().getLp() <= 0){
            looser = game.getPlayer2();
            winner = game.getPlayer1();
            return endRound(looser,winner);
        }
        return null;
    }

    private ApiMessage endRound(Player looser, Player winner) throws Exception {
        JSONObject ans = new JSONObject();
        ans.put("isOver",true);
        AccountJson looserAccount = programController.getUserInfoByNickname(looser.getNickname());
        AccountJson winnerAccount = programController.getUserInfoByNickname(winner.getNickname());
        ans.put("winner",winnerAccount.getUsername());
        winnerAccount.increaseMoney(1000+winner.getLp());
        winnerAccount.increaseScore(1000);
        looserAccount.increaseMoney(100);
        programController.changeUserInfoInDataBase(looserAccount);
        programController.changeUserInfoInDataBase(winnerAccount);
        game = null;
        return new ApiMessage(ApiMessage.successful,ans.toString());
    }

    public ApiMessage activateEffect() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(game.getActivePlayer().getSelectedCard().getCategory() != Category.SPELL)
            return new ApiMessage(ApiMessage.error,"activate effect is only for spell cards.");

        if(game.getPhase() != Phase.MAIN_PHASE_1)
            return new ApiMessage(ApiMessage.error,"you can’t activate an effect on this turn");

        var selectedCard = (SpellCard) game.getActivePlayer().getSelectedCard();

        if(selectedCard.isActivateInTurn())
            return new ApiMessage(ApiMessage.error,"you have already activated this card");

        if(game.getActivePlayer().getField().getCntFreeCellsInSpellZone() == 0 &&
                game.getActivePlayer().getField().isSpellZoneContains(selectedCard) &&
                selectedCard.getIcon() != Icon.FIELD)
            return new ApiMessage(ApiMessage.error,"spell card zone is full");

        game.addToGameLog(GameLogType.ACTIVE_EFFECT,selectedCard.hashCode());
        if(selectedCard.getIcon() == Icon.FIELD){
            game.getActivePlayer().activateField(game,selectedCard);
        }
        else{
            game.getActivePlayer().activateSpell(game,selectedCard);
        }
        return new ApiMessage(ApiMessage.successful,"spell activated");
    }

    public ApiMessage setSpellAndTrap() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(!game.getActivePlayer().getHand().contains(game.getActivePlayer().getSelectedCard()))
            return new ApiMessage(ApiMessage.error,"you can’t set this card");

        if(game.getPhase() != Phase.MAIN_PHASE_1 && game.getPhase() != Phase.MAIN_PHASE_2)
            return new ApiMessage(ApiMessage.error,"you can’t do this action in this phase");

        if(game.getActivePlayer().getField().getCntFreeCellsInSpellZone() == 0)
            return new ApiMessage(ApiMessage.error,"spell card zone is full");


        game.addToGameLog(GameLogType.SET_SPELL,game.getActivePlayer().getSelectedCard().hashCode());
        game.getActivePlayer().setSpell((SpellCard) game.getActivePlayer().getSelectedCard());
        return new ApiMessage(ApiMessage.successful,"set successfully");
    }

    public void ritualSummonMonster(){
    }

    public ApiMessage getBoard() throws  Exception {
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(new BoardJson(new FieldJson(game.getActivePlayer()),new FieldJson(game.getInactivePlayer()))));
    }

    public ApiMessage getGraveyard() throws Exception {
        ArrayList<CardGeneralInfo> ans = new ArrayList<>();
        for (Card card : game.getActivePlayer().getField().getGraveyard()) {
            ans.add(getCardGeneralInfoFromCard(card));
        }
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(ans));
    }

    public ApiMessage getSelectedCard() throws Exception {
        if(game.getActivePlayer().getSelectedCard() == null)
            return new ApiMessage(ApiMessage.error,"no card is selected yet");

        if(game.getInactivePlayer().containCard(game.getActivePlayer().getSelectedCard()) &&
        game.getActivePlayer().getSelectedCard().getStatus() == Status.SET)
            return new ApiMessage(ApiMessage.error,"card is not visible");

        JSONObject ans = new JSONObject();
        ans.put("mainInfo",new Gson().toJson(getCardGeneralInfoFromCard(game.getActivePlayer().getSelectedCard())));
        if(game.getActivePlayer().getSelectedCard().getCategory() == Category.MONSTER){
            ans.put("attack",((MonsterCard) game.getActivePlayer().getSelectedCard()).getAtk());
            ans.put("defense",((MonsterCard) game.getActivePlayer().getSelectedCard()).getDef());
        }
        return new ApiMessage(ApiMessage.successful,ans.toString());
    }

    public ApiMessage getPhase() throws Exception {
        return new ApiMessage(ApiMessage.successful, new Gson().toJson(game.getPhase()));
    }

    private CardGeneralInfo getCardGeneralInfoFromCard(Card card){
        return new CardGeneralInfo(card.getName(),card.getDescription());
    }

    public ArrayList<String> cardJsonToString(ArrayList<CardJson> deck){
        ArrayList<String> arrayList = new ArrayList<String>();
        for(CardJson card : deck){
            arrayList.add(card.getName());
        }
        return arrayList;
    }
}