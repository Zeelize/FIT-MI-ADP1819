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
        if (!(this.gameModel instanceof GameModel)) return;

        switch(evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameModel.moveCannonUp();
                break;

            case KeyEvent.VK_DOWN:
                this.gameModel.moveCannonDown();
                break;

                default:
                    break;
        }
    }
}
