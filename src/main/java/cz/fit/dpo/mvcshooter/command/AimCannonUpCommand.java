package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class AimCannonUpCommand extends AbsGameCommand {

    public AimCannonUpCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.aimCanonUp();
    }
}
