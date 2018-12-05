package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class MoveUpCommand extends AbsGameCommand {

    public MoveUpCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.moveCannonUp();
    }
}
