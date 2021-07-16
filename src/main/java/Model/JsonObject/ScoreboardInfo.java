package Model.JsonObject;

public class ScoreboardInfo {
    public final String nickname;
    public final int score;
    public ScoreboardInfo(String nickname, int score){
        this.nickname = nickname;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getNickname() {
        return nickname;
    }
}
