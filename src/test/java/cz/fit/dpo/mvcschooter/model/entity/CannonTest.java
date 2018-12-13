package cz.fit.dpo.mvcschooter.model.entity;

import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.model.entity.Cannon;
import cz.fit.dpo.mvcshooter.state.DoubleShootingMode;
import cz.fit.dpo.mvcshooter.state.SingleShootingMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CannonTest {
    @InjectMocks
    private GameModel model;

    @Test
    public void aimUpTest() {
        Cannon c = model.getCannon();
        c.aimUp();
        Assert.assertEquals(GameConfig.INIT_ANGLE + c.getAngleStep(), c.getAngle(), 0.0000001);
    }

    @Test
    public void incPowerTest() {
        Cannon c = model.getCannon();
        c.incPower();
        Assert.assertEquals(GameConfig.INIT_SPEED + c.getSpeedStep(), c.getSpeed(), 0.0000001);
    }

    @Test
    public void changeModeTest() {
        Cannon c = model.getCannon();
        Assert.assertTrue(c.getShootingMode() instanceof SingleShootingMode);

        c.toggleShootingMode();
        Assert.assertTrue(c.getShootingMode() instanceof DoubleShootingMode);
    }
}
