package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.entity.Cannon;

public class DoubleShootingMode implements IShootingMode {
    @Override
    public void shoot(Cannon cannon) {
        cannon.setAngle(cannon.getAngle() - 5);
        cannon.primitiveShoot();
        cannon.setAngle(cannon.getAngle() + 10);
        cannon.primitiveShoot();
        cannon.setAngle(cannon.getAngle() - 5);
    }

    @Override
    public void nextMode(Cannon cannon) {
        cannon.setSingleShootingMode();
    }
}
