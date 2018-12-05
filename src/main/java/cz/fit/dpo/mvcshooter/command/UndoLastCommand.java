package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class UndoLastCommand extends AbsGameCommand {

    public UndoLastCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.undoLastCmd();
    }
}
