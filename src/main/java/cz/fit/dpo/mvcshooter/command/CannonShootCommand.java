package cz.fit.dpo.mvcshooter.command;

import cz.fit.dpo.mvcshooter.proxy.IGameModel;

public class CannonShootCommand extends AbsGameCommand {

    public CannonShootCommand(IGameModel subject) {
        super(subject);
    }

    @Override
    public void execute() {
        subject.shootCanon();
    }
}
