package cz.fit.dpo.mvcshooter.model.entity;

public class GameInfo extends GameObject {

    private int score;

    public GameInfo(int score) {
        this.score = score;
        this.setPosX(10);
        this.setPosY(10);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return "Score: " + this.score;

    }
}
