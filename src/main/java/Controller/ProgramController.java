package Controller;
import Model.ApiMessage;
import Model.JsonObject.AccountJson;
import Model.JsonObject.ScoreboardInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ProgramController {
    private final String usersInfoPath = "..\\Database\\UserInfo.txt";
    private AccountJson loggedInUser ;

    // ToDo: get corrct value for every fucntion from matcher
    public ApiMessage register(Matcher matcher) throws Exception {
        String password = "";
        String nickname = "";
        String username = "";

        if(doesUserWithThisUsernameExist(username)){
            return new ApiMessage(ApiMessage.error,"user with username " + username + " already exists");
        }

        if(doesUserWithThisNicknameExist(nickname)){
            return new ApiMessage(ApiMessage.error, "user with nickname " + nickname + " already exists");
        }

        addUserInfoToDatabase(username , password , nickname);
        return new ApiMessage(ApiMessage.successful, "user created successfully!");
    }


    public ApiMessage login(Matcher matcher) throws Exception {
        String username = "";
        String password = "";

        if(!doesUserWithThisUsernameExist(username)){
            return new ApiMessage(ApiMessage.error,"Username and password didn’t match!");
        }

        if(!isCorrectPassword(username, password)){
            return new ApiMessage(ApiMessage.error,"Username and password didn’t match!");
        }

        setLoggedInUser(getUserInfoByUsername(username));
        return new ApiMessage(ApiMessage.successful, "user logged in successfully!");
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
        loggedInUser.setNickname(nickname);
        changeUserInfoInDataBase(loggedInUser);
        return new ApiMessage(ApiMessage.successful,"nickname changed successfully!");
    }


    public ApiMessage changePassword(Matcher matcher) throws Exception {
        String currentPassword = "" ;
        String newPassword = "";

        if(!loggedInUser.getPassword().equals(currentPassword)){
            return new ApiMessage(ApiMessage.error,"current password is invalid");
        }

        if(loggedInUser.getPassword().equals(newPassword)){
            return new ApiMessage(ApiMessage.error,"please enter a new password");
        }

        loggedInUser.setPassword(newPassword);
        changeUserInfoInDataBase(loggedInUser);
        return new ApiMessage(ApiMessage.successful,"password changed successfully!");
    }

    public void createDeck(Matcher matcher){

    }

    public void deleteDeck(Matcher matcher){
        
    }

    public void activeDeck(Matcher matcher){
        
    }

    public void addCard(Matcher matcher){

    }

    public void removeCard(Matcher matcher){

    }

    public void showDeck(Matcher matcher){

    }

    public void showPurchasedCards(){

    }

    public void buyCard(Matcher matcher){

    }

    public void showShopCards(Matcher matcher){

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

    public ArrayList<AccountJson> getUsersInfo() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(usersInfoPath)));
        return new Gson().fromJson(json ,new TypeToken<List<AccountJson>>(){}.getType());
    }

    public void setLoggedInUser(AccountJson loggedInUser){
        this.loggedInUser = loggedInUser;
    }
}
