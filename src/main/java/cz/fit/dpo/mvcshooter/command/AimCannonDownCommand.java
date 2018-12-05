package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class AimCannonDownCommand extends AbsGameCommand {

    public AimCannonDownCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.aimCanonDown();
    }
}
