package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class IncCannonPowerCommand extends AbsGameCommand {

    public IncCannonPowerCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.incCanonPower();
    }
}
