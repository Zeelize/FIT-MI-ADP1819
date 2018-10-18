package cz.fit.dpo.mvcshooter.model;

public class ModelInfo extends GameObject {

    private int score;

    public ModelInfo(int score) {
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
