package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class SwitchStrategyCommand extends AbsGameCommand {

    public SwitchStrategyCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.switchMovementStrategy();
    }
}
