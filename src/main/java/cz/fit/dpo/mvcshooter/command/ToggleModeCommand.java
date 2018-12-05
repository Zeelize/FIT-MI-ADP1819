package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class ToggleModeCommand extends AbsGameCommand {

    public ToggleModeCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.toggleShootingMode();
    }
}
