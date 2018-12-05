package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.command.*;
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

        switch(evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameModel.registerCmd(new MoveUpCommand(this.gameModel));
                break;

            case KeyEvent.VK_DOWN:
                this.gameModel.registerCmd(new MoveDownCommand(this.gameModel));
                break;

            case KeyEvent.VK_SPACE:
                this.gameModel.registerCmd(new CannonShootCommand(this.gameModel));
                break;

            case KeyEvent.VK_W:
                this.gameModel.registerCmd(new AimCannonDownCommand(this.gameModel));
                break;
            case KeyEvent.VK_BACK_SPACE:
                // todo fix
                this.gameModel.registerCmd(new UndoLastCommand(this.gameModel));
                break;
            case KeyEvent.VK_S:
                this.gameModel.registerCmd(new AimCannonUpCommand(this.gameModel));
                break;
            case KeyEvent.VK_A:
                this.gameModel.registerCmd(new DecCannonPowerCommand(this.gameModel));
                break;
            case KeyEvent.VK_D:
                this.gameModel.registerCmd(new IncCannonPowerCommand(this.gameModel));
                break;
            case KeyEvent.VK_Q:
                this.gameModel.registerCmd(new SwitchStrategyCommand(this.gameModel));
                break;
            case KeyEvent.VK_E:
                this.gameModel.registerCmd(new ToggleModeCommand(this.gameModel));
                break;
            case KeyEvent.VK_R:
                this.gameModel.registerCmd(new CannonReloadCommand(this.gameModel));
                break;
            case KeyEvent.VK_P:
                this.gameModel.registerCmd(new PauseResumeGameCommand(this.gameModel));
                break;
        }
    }
}
