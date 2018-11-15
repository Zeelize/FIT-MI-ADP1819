package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.entity.Cannon;

public class DoubleShootingMode implements IShootingMode {
    @Override
    public void shoot(Cannon cannon) {
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.aimDown();
        cannon.aimDown();
        cannon.aimDown();
        cannon.aimDown();
        cannon.aimDown();
        cannon.primitiveShoot();
    }

    @Override
    public void nextMode(Cannon cannon) {
        cannon.setSingleShootingMode();
    }
}
