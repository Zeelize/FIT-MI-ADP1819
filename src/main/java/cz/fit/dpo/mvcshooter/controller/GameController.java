package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.GameModel;

import java.awt.event.KeyEvent;

public class GameController {

    private GameModel gameModel;

    public GameController(final GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void onKeyPressed(KeyEvent evt) {
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameModel.moveCannonUp();
                break;

            case KeyEvent.VK_DOWN:
                gameModel.moveCannonDown();
                break;

                default:
                    break;
        }
    }
}
