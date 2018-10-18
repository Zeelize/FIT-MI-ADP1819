package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.GameModel;

import java.awt.event.KeyEvent;

public class GameController {

    private GameModel gameModel;

    public GameController() {

    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void onKeyPressed(KeyEvent evt) {
        if (this.gameModel == null) return;

        switch(evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameModel.moveCannonUp();
                break;

            case KeyEvent.VK_DOWN:
                this.gameModel.moveCannonDown();
                break;

            case KeyEvent.VK_SPACE:
                System.out.println("Space pressed!");
                break;
        }
    }
}
