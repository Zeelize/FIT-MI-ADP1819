package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class PauseResumeGameCommand extends AbsGameCommand {

    public PauseResumeGameCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.pauseResumeGame();
    }
}
