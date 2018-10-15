package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.Cannon;

public class GameModel {
    private Cannon cannon = new Cannon();

    public Cannon getCannon() {
        return cannon;
    }

    public void moveCannonDown() {
        // TODO Jump out of bounds
        cannon.setPosY(cannon.getPosY() + 20);
    }

    public void moveCannonUp() {
        if (cannon.getPosY() - 20 < 0) System.out.println("out of zone");
        cannon.setPosY(cannon.getPosY() - 20);
    }
}
