package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.entity.Cannon;

public class SingleShootingMode implements IShootingMode {
    @Override
    public void shoot(Cannon cannon) {
        cannon.primitiveShoot();
    }

    @Override
    public void nextMode(Cannon cannon) {
        cannon.setDoubleShootingMode();
    }
}
