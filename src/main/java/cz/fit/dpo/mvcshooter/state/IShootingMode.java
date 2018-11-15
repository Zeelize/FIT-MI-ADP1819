package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.entity.Cannon;

public interface IShootingMode {
    void shoot(Cannon cannon);

    void nextMode(Cannon cannon);
}
