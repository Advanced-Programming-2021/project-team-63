package Controller;
import Model.ApiMessage;
import Model.JsonObject.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ProgramController {
    private final String usersInfoPath = "src\\main\\java\\Database\\UserInfo.txt";
    private final String monstersInfoPath = "src\\main\\java\\Database\\MonstersInfo.txt";
    private final String spellAndTrapsINfoPath = "src\\main\\java\\Database\\Spell&TrapsInfo.txt";
    private AccountJson loggedInUser ;

    // ToDo: get corrct value for every fucntion from matcher
    public ApiMessage register(Matcher matcher) throws Exception {
        String password = "";
        String nickname = "";
        String username = "";

        if(doesUserWithThisUsernameExist(username)){
            return new ApiMessage(ApiMessage.error,"user with username " + username + " already exists");
        }

        else if(doesUserWithThisNicknameExist(nickname)){
            return new ApiMessage(ApiMessage.error, "user with nickname " + nickname + " already exists");
        }

        else{
            addUserInfoToDatabase(username , password , nickname);
            return new ApiMessage(ApiMessage.successful, "user created successfully!");
        }

    }

    public ApiMessage login(Matcher matcher) throws Exception {
        String username = "";
        String password = "";

        if(!doesUserWithThisUsernameExist(username)){
            return new ApiMessage(ApiMessage.error,"Username and password didn’t match!");
        }

        else if(!isCorrectPassword(username, password)){
            return new ApiMessage(ApiMessage.error,"Username and password didn’t match!");
        }

        else{
            setLoggedInUser(getUserInfoByUsername(username));
            return new ApiMessage(ApiMessage.successful, "user logged in successfully!");
        }

    }

    public ApiMessage logout(Matcher matcher) throws Exception {
        setLoggedInUser(null);
        return new ApiMessage(ApiMessage.successful,"user logged out successfully!");
    }

    public ApiMessage showScoreboard(Matcher matcher) throws Exception {
        var users = getUsersInfo();
        ArrayList<ScoreboardInfo> scoreboard = (ArrayList<ScoreboardInfo>) users.stream().map(x -> new ScoreboardInfo(x.getNickname(),x.getScore())).collect(Collectors.toList());
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(scoreboard));
    }

    public ApiMessage changeNickname(Matcher matcher) throws Exception {
        String nickname = "" ;
        if(doesUserWithThisNicknameExist(nickname)){
            return new ApiMessage(ApiMessage.error,"user with nickname " + nickname + " already exists");
        }
        else{
            loggedInUser.setNickname(nickname);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"nickname changed successfully!");
        }
    }

    public ApiMessage changePassword(Matcher matcher) throws Exception {
        String currentPassword = "" ;
        String newPassword = "";

        if(!loggedInUser.getPassword().equals(currentPassword)){
            return new ApiMessage(ApiMessage.error,"current password is invalid");
        }

        else if(loggedInUser.getPassword().equals(newPassword)){
            return new ApiMessage(ApiMessage.error,"please enter a new password");
        }

        else{
            loggedInUser.setPassword(newPassword);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"password changed successfully!");
        }
    }

    public ApiMessage createDeck(Matcher matcher) throws Exception {
        String deckName = "";

        if(loggedInUser.getDeckByName(deckName) == null){
            return new ApiMessage(ApiMessage.error,"deck with name "+deckName+"  already exists");
        }

        else{
            loggedInUser.addDeck(deckName);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful, "deck created successfully!");
        }
    }

    public ApiMessage deleteDeck(Matcher matcher) throws Exception {
        String deckName = "";

        var deck = loggedInUser.getDeckByName(deckName);

        if(deck == null){
            return new ApiMessage(ApiMessage.error,"deck with name "+deckName+"  does not exist");
        }

        else{
            loggedInUser.deleteDeck(deck);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"deck deleted successfully");
        }
    }

    public ApiMessage selectActiveDeck(Matcher matcher) throws Exception {
        String deckName = "";
        var deck = loggedInUser.getDeckByName(deckName);

        if(deck == null){
            return new ApiMessage(ApiMessage.error,"deck with name " + deckName + " does not exist");
        }

        else{
            loggedInUser.setActiveDeck(deck);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"deck activated successfully");
        }
    }

    public ApiMessage addCardToDeck(Matcher matcher) throws Exception {
        String cardName = "";
        String deckName = "";
        boolean inSideDeck = false;
        var deck = loggedInUser.getDeckByName(deckName);

        if(!loggedInUser.doesHaveThisCard(cardName)){
            return new ApiMessage(ApiMessage.error,"card with name "+cardName+" does not exist");
        }

        else if(deck == null){
            return new ApiMessage(ApiMessage.error,"deck with name "+deckName+" does not exist");
        }

        else if(!inSideDeck && deck.isMainDeckFull()){
            return new ApiMessage(ApiMessage.error,"main deck is full");
        }

        else if(inSideDeck && deck.isSideDeckFull()){
            return new ApiMessage(ApiMessage.error,"side deck is full");
        }

        else if(deck.getCntOfThisCard(cardName) == 3){
            return new ApiMessage(ApiMessage.error,"there are already three cards with name "+cardName+" in deck "+deckName);
        }

        else{
            if(inSideDeck)
                deck.addToSideDeck(cardName);
            else
                deck.addToMainDeck(cardName);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"card added to deck successfully");
        }
    }

    public ApiMessage removeCardFromDeck(Matcher matcher) throws Exception {
        //hame ro hazf konim ya yekisho
        //manaye mojoodi chiye
        String cardName = "";
        String deckName = "";
        boolean fromSideDeck = false;
        var deck = loggedInUser.getDeckByName(deckName);

        if(deck == null){
            return new ApiMessage(ApiMessage.error,"deck with name "+deckName+" does not exist");
        }

        else if(!fromSideDeck && deck.getCntOfThisCardInMainDeck(cardName) == 0){
            return new ApiMessage(ApiMessage.error,"card with name "+cardName+"  does not exist in main deck");
        }

        else if(fromSideDeck && deck.getCntOfThisCardInSideDeck(cardName) == 0){
            return new ApiMessage(ApiMessage.error,"card with name "+cardName+"  does not exist in side deck");
        }

        else{
            if(!fromSideDeck)
                deck.removeFromMainDeck(cardName);
            else
                deck.removeFromSideDeck(cardName);
            changeUserInfoInDataBase(loggedInUser);
            return new ApiMessage(ApiMessage.successful,"card removed form deck successfully");
        }

    }

    public ApiMessage showAllDeck(Matcher matcher) throws Exception {
        //how fix Gson null values
        var ans = new ShowAllDecksJson(loggedInUser);
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(ans));
    }

    public ApiMessage showDeck(Matcher matcher) throws Exception {
        String deckName = "";
        boolean isSideDeck = false;
        var deck = loggedInUser.getDeckByName(deckName);

        if(deck == null){
            return new ApiMessage(ApiMessage.error,"deck with name "+deckName+" does not exist");
        }
        else{
            var ans = new ShowDeckJson(deckName , isSideDeck);
            if(!isSideDeck){
                getCardGeneralInfoForShowDeck(ans , deck.getMainDeck());
            }
            else{
                getCardGeneralInfoForShowDeck(ans , deck.getSideDeck());
            }
            return new ApiMessage(ApiMessage.successful,new Gson().toJson(ans));
        }
    }


    public ApiMessage showPurchasedCards() throws Exception {
        ArrayList<CardGeneralInfo> purchaseCards =  new ArrayList<>();
        for (CardJson card : loggedInUser.getPurchasedCards()) {
            if(isMonsterExistWithThisName(card.getName())){
                MonsterJson monster = getMonsterByName(card.getName());
                purchaseCards.add(new CardGeneralInfo(monster));
            }
            else{
                SpellAndTrapJson spellAndTrap = getSpellAndTrapByName(card.getName());
                purchaseCards.add(new CardGeneralInfo(spellAndTrap));
            }
        }
        return new ApiMessage(ApiMessage.successful,new Gson().toJson(purchaseCards));
    }

    public ApiMessage buyCard(Matcher matcher) throws Exception {
        String cardName = "";

        if(!isMonsterExistWithThisName(cardName)&&!isSpellOrTrapExsistWithThisName(cardName)){
            return new ApiMessage(ApiMessage.error,"there is no card with this name");
        }

        CardGeneralInfo card;
        if(isMonsterExistWithThisName(cardName)){
            MonsterJson monster = getMonsterByName(cardName);
            card = new CardGeneralInfo(monster);
        }
        else{
            SpellAndTrapJson spellAndTrap = getSpellAndTrapByName(cardName);
            card = new CardGeneralInfo(spellAndTrap);
        }

        if(loggedInUser.getMoney() < card.getPrice()){
            return new ApiMessage(ApiMessage.error,"not enough money");
        }

        loggedInUser.addToPurchasedCards(cardName);
        loggedInUser.decrease(card.getPrice());
        changeUserInfoInDataBase(loggedInUser);
        return new ApiMessage(ApiMessage.successful,"card successfully purchased");
    }

    public ApiMessage showShopCards(Matcher matcher) throws Exception {
        ArrayList<CardGeneralInfo> ans = new ArrayList<>();

        for (MonsterJson monster : getMonstersInfo()) {
            ans.add(new CardGeneralInfo(monster));
        }

        for (SpellAndTrapJson spellAndTrap : getSpellAndTrapsInfo()) {
            ans.add(new CardGeneralInfo(spellAndTrap));
        }

        return new ApiMessage(ApiMessage.successful,new Gson().toJson(ans));
    }

    private void getCardGeneralInfoForShowDeck(ShowDeckJson ans, ArrayList<CardJson> deck) throws IOException {
        for (CardJson card : deck) {
            if(isMonsterExistWithThisName(card.getName())){
                MonsterJson monster = getMonsterByName(card.getName());
                ans.addToMonsters(new CardGeneralInfo(monster));
            }
            else{
                SpellAndTrapJson spellAndTrap = getSpellAndTrapByName(card.getName());
                ans.addToSpellAndTraps(new CardGeneralInfo(spellAndTrap));
            }
        }
    }

    private boolean isMonsterExistWithThisName(String name) throws IOException {
        var monsters = getMonstersInfo();
        for (MonsterJson monster : monsters) {
            if(monster.getName().equals(name))
                return true;
        }
        return false ;
    }

    private boolean isSpellOrTrapExsistWithThisName(String name) throws IOException {
        var spellAndTraps = getSpellAndTrapsInfo();
        for (SpellAndTrapJson spellAndTrap : spellAndTraps) {
            if(spellAndTrap.getName().equals(name))
                return true;
        }
        return false ;
    }

    private MonsterJson getMonsterByName(String name) throws IOException {
        var monsters = getMonstersInfo();
        for (MonsterJson monster : monsters) {
            if(monster.getName().equals(name))
                return monster;
        }
        return null;
    }

    private SpellAndTrapJson getSpellAndTrapByName(String name) throws IOException {
        var spellAndTraps = getSpellAndTrapsInfo();
        for (SpellAndTrapJson spellAndTrap : spellAndTraps) {
            if(spellAndTrap.getName().equals(name))
                return spellAndTrap;
        }
        return null;
    }

    private ArrayList<MonsterJson> getMonstersInfo() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(monstersInfoPath)));
        return new Gson().fromJson(json ,new TypeToken<List<MonsterJson>>(){}.getType());
    }

    private ArrayList<SpellAndTrapJson> getSpellAndTrapsInfo() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(spellAndTrapsINfoPath)));
        return new Gson().fromJson(json ,new TypeToken<List<SpellAndTrapJson>>(){}.getType());
    }

    private void changeUserInfoInDataBase(AccountJson newUserInfo) throws IOException {
        ArrayList<AccountJson> users = getUsersInfo();
        FileWriter fileWriter = new FileWriter(usersInfoPath);
        for (AccountJson user : users) {
            if(user.getUsername().equals(newUserInfo.getUsername())){
                user = newUserInfo;
                break;
            }
        }
        fileWriter.write(new Gson().toJson(users));
        fileWriter.close();
    }

    public boolean doesUserWithThisUsernameExist(String username) throws IOException {
       ArrayList<AccountJson> users = getUsersInfo();
        for (AccountJson user : users) {
            if(user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean doesUserWithThisNicknameExist(String nickname) throws IOException {
        ArrayList<AccountJson> users = getUsersInfo();
        for (AccountJson user : users) {
            if(user.getNickname().equals(nickname))
                return true;
        }
        return false;
    }

    private void addUserInfoToDatabase(String username, String password, String nickname) throws IOException {
        AccountJson newUser = new AccountJson(username, password, nickname);
        ArrayList<AccountJson> users = getUsersInfo();
        FileWriter fileWriter = new FileWriter(usersInfoPath);

        users.add(newUser);
        fileWriter.write(new Gson().toJson(users));
        fileWriter.close();
    }

    private boolean isCorrectPassword(String username, String password) throws IOException {
        AccountJson user = getUserInfoByUsername(username);
        return user.getPassword().equals(password);
    }

    private AccountJson getUserInfoByUsername(String username) throws IOException {
        ArrayList<AccountJson> users = getUsersInfo();
        for (AccountJson user : users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    private ArrayList<AccountJson> getUsersInfo() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(usersInfoPath)));
        return new Gson().fromJson(json ,new TypeToken<List<AccountJson>>(){}.getType());
    }

    private void setLoggedInUser(AccountJson loggedInUser){
        this.loggedInUser = loggedInUser;
    }



}
