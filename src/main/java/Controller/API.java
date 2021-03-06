package Controller;

import Model.ApiMessage;
import Model.Game.Card.MonsterCard.Mode;
import Model.Game.CardAddress;
import org.json.JSONObject;

public class API {
    private static boolean justOneObject = false;
    private GameController gameController;
    private ProgramController programController;
    private String request;

    public API(){
        assert !justOneObject;
        justOneObject = true;
        programController = new ProgramController();
        gameController = new GameController(programController);
    }

    public JSONObject run(JSONObject request) throws Exception {
        String commandType = request.getString("command");
        //import cards
        if(commandType.equals("show_scorboard")){
            return new JSONObject(programController.showScoreboard());
        }
        if(commandType.equals("next_phase")) {
            return new JSONObject(gameController.nextPhase());
        }
        if(commandType.equals("buyCard")){
            return new JSONObject(programController.buyCard((String) request.get("cardName")));
        }
        if(commandType.equals("get_phase")){
            return new JSONObject(gameController.getPhase());
        }
        if(commandType.equals("shop_show_all")) {
            return new JSONObject(programController.showShopCards());
        }
        if(commandType.equals("crate_deck")){
            return new JSONObject(programController.createDeck((String) request.get("deckName")));
        }
        if(commandType.equals("delete_deck")){
            return new JSONObject(programController.deleteDeck((String) request.get("deckName")));
        }
        if(commandType.equals("set_deck_activate")){
            return new JSONObject(programController.selectActiveDeck((String) request.get("deckName")));
        }
        if(commandType.equals("show_deck_all")){
            return new JSONObject(programController.showAllDeck());
        }
        if(commandType.equals("summon_card")){
            return new JSONObject(gameController.summonMonster());
        }
        if(commandType.equals("flip_summon_card")){
            return new JSONObject(gameController.flipSummonMonster());
        }
        if(commandType.equals("back_select")){
            return new JSONObject(gameController.deselectCard());
        }
        if(commandType.equals("show_selected_card")){
            return new JSONObject(gameController.getSelectedCard());
        }
        if(commandType.equals("attack")){
            return new JSONObject(gameController.attack(request.getInt("place")));
        }
        if(commandType.equals("attack_direct")){
            return new JSONObject(gameController.directAttack());
        }
        if(commandType.equals("active_effect")){
            return new JSONObject(gameController.activateEffect());
            //check
        }
        if(commandType.equals("show_graveyard")){
            return new JSONObject(gameController.getGraveyard());
        }
        if(commandType.equals("increase_money")){
            return new JSONObject(programController.increaseMoney( Integer.parseInt( request.get("amount").toString())));
        }
        if(commandType.equals("set_winner")){
            //what to do
        }
        if(commandType.equals("get_board")){
            return new JSONObject(gameController.getBoard());
        }
        if(commandType.equals("crate_new_user")){
            String username = (String) request.get("username");
            String password = (String) request.get("password");
            String nickname = (String) request.get("nickname");
            return new JSONObject(programController.register(username,password,nickname));
        }
        if(commandType.equals("login_user")){
            String username = (String) request.get("username");
            String password = (String) request.get("password");
            return new JSONObject(programController.login(username,password));
        }
        if(commandType.equals("change_Profile_nickname")){
            return new JSONObject(programController.changeNickname((String) request.get("nickname")));
        }
        if(commandType.equals("change_Profile_password")){
            return new JSONObject(programController.changePassword((String) request.get("currentPass"),(String) request.get("newPass")));
        }
        if(commandType.equals("add_card_deck")){
            String deckName = (String) request.get("deckName");
            String cardName = (String) request.get("cardName");
            boolean isSideDeck = !(Boolean.parseBoolean( request.get("main_side_?").toString()));
            return new JSONObject(programController.addCardToDeck(cardName,deckName,isSideDeck));
        }
        if(commandType.equals("remove_card_deck")){
            String deckName = (String) request.get("deckName");
            String cardName = (String) request.get("cardName");
            boolean isSideDeck = !(Boolean.parseBoolean( request.get("main_side_?").toString()));
            return new JSONObject(programController.removeCardFromDeck(cardName,deckName,isSideDeck));
        }
        if(commandType.equals("show_deck")){
            String deckName = (String) request.get("deckName");
            boolean isSideDeck = !(Boolean.parseBoolean( request.get("main_side_?").toString()));
            return new JSONObject(programController.showDeck(deckName,isSideDeck));
        }
        if(commandType.equals("show_deck_all")){
            return new JSONObject(programController.showAllDeck());
        }
        if(commandType.equals("duel_new_game")){
            String opponent = (String) request.get("Opponent");
            int rounds = Integer.parseInt( request.get("round").toString());
            return new JSONObject(programController.createDuel(opponent,rounds,gameController));
            //ai
        }
        if(commandType.equals("set_winner")){
            String nickname = (String) request.get("who?");
            gameController.isRoundOver();
        }
        if(commandType.equals("set_card")){
            return new JSONObject(gameController.summonMonster());
        }
        if(commandType.equals("set_position")){
            Mode monsterMode = null;
            if(((String) request.get("position")).equals("attack"))
                monsterMode = Mode.ATTACK;
            else
                monsterMode = Mode.DEFENSE;
            return new JSONObject(gameController.changeMonsterMode(monsterMode));
        }
        if(commandType.equals("add_card_to_hand")){
            return new JSONObject(gameController.addCardFromDeckToHand());
        }
        if(commandType.equals("tribute")){
            int numberOfTribute = request.getInt("number");
            if(numberOfTribute == 1){
                int address1 = request.getInt("address1");
                return new JSONObject(gameController.getTributeForSummonMonster(address1));
            }
            else{
                int address1 = request.getInt("address1");
                int address2 = request.getInt("address2");
                return new JSONObject(gameController.getTributesForSummonMonster(address1,address2));
            }
        }
        if(commandType.equals("select_card")){
            String zone = (String) request.get("zone");
            CardAddress selectedCardAddress = null;
            switch (zone){
                case "monster_zone":
                    selectedCardAddress = CardAddress.MONSTER_ZONE;
                    break;
                case "spell_zone":
                    if(!(Boolean.parseBoolean( request.get("side").toString())))
                        selectedCardAddress = CardAddress.SPELL_ZONE;
                    else
                        selectedCardAddress = CardAddress.OPPONENT_SPELL_ZONE;
                    break;
                case "hand_zone":
                    selectedCardAddress = CardAddress.HAND;
                    break;
                case "field_zone":
                    if(!(Boolean.parseBoolean( request.get("side").toString())))
                        selectedCardAddress = CardAddress.FIELD_ZONE;
                    else
                        selectedCardAddress = CardAddress.OPPONENT_FIELD_ZONE;
                    break;
            }
            return new JSONObject(gameController.selectCard(selectedCardAddress, Integer.parseInt( request.get("place").toString())));
        }
        return null;
    }
}
