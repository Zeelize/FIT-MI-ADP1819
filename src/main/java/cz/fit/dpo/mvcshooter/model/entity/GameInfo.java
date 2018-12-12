package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class GameInfo extends GameObject {

    private GameModel model;

    public GameInfo(GameModel model) {
        this.model = model;
        this.setPosX(10);
        this.setPosY(10);
    }

    public String getText() {
        String text = "Score: " + this.model.getScore()
                + " Angle: " + this.model.getCannon().getAngle()
                + " Speed: " + this.model.getCannon().getSpeed()
                + " Enemies: " + this.model.getEnemies().size()
                + " Missiles: " + this.model.getMissiles().size()
                + " Magazine: " + this.model.getCannon().getMagazine() + "/" + GameConfig.MAGAZINE_SIZE
                + " Time: " + this.model.getStopwatch() + " seconds";

        if (this.model.getPause()) {
            text += " -----PAUSED-----";
        }

        if (!this.model.getRunGame()) {
            text += " -----TO START NEW GAME, PRESS <ENTER>-----";
        }
        return text;
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
