package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.command.CannonShootCommand;
import cz.fit.dpo.mvcshooter.proxy.IGameModel;

import java.awt.event.KeyEvent;

public class GameController {

    private IGameModel gameModel;

    public GameController() {
    }

    public void setGameModel(IGameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void onKeyPressed(KeyEvent evt) {
        if (this.gameModel == null) return;

        // todo commands for all
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameModel.moveCannonUp();
                break;

            case KeyEvent.VK_DOWN:
                this.gameModel.moveCannonDown();
                break;

            case KeyEvent.VK_SPACE:
                this.gameModel.registerCmd(new CannonShootCommand(this.gameModel));
                break;

            case KeyEvent.VK_W:
                this.gameModel.aimCanonDown();
                break;
            case KeyEvent.VK_BACK_SPACE:
                this.gameModel.undoLastCmd();
                break;
            case KeyEvent.VK_S:
                this.gameModel.aimCanonUp();
                break;
            case KeyEvent.VK_A:
                this.gameModel.decCannonPower();
                break;
            case KeyEvent.VK_D:
                this.gameModel.incCanonPower();
                break;
            case KeyEvent.VK_Q:
                this.gameModel.switchMovementStrategy();
                break;
            case KeyEvent.VK_E:
                this.gameModel.toggleShootingMode();
                break;
            case KeyEvent.VK_R:
                // todo reload and do a stack of bullets
                break;
        }
    }
}
