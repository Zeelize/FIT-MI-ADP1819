package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class DecCannonPowerCommand extends AbsGameCommand {

    public DecCannonPowerCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.decCannonPower();
    }
}
