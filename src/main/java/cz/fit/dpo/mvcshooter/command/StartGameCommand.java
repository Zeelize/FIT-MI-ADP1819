package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class StartGameCommand extends AbsGameCommand {

    public StartGameCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.startGame();
    }
}
