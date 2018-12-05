package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class CannonReloadCommand extends AbsGameCommand {

    public CannonReloadCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.reloadCannon();
    }
}
