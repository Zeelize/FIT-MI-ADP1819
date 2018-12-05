package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class MoveDownCommand extends AbsGameCommand {

    public MoveDownCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.moveCannonDown();
    }
}
