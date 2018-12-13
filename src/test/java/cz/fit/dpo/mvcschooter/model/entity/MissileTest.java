package cz.fit.dpo.mvcschooter.model.entity;

import cz.fit.dpo.mvcshooter.model.entity.Missile;
import cz.fit.dpo.mvcshooter.strategy.SimpleMovementStrategy;
import org.junit.Assert;
import org.junit.Test;

public class MissileTest {
    @Test
    public void simpleMoveTest() {
        Missile m = new Missile(20, 20, 10.0f, 0.0f, new SimpleMovementStrategy());

        int nx = (int) (m.getInitX() + (m.getSpeed()) * (m.getTime() + 1) * Math.cos(Math.toRadians(m.getAngle())));
        int ny = (int) (m.getInitY() + (m.getSpeed()) * (m.getTime() + 1) * Math.sin(Math.toRadians(m.getAngle())));
        m.move();

        Assert.assertEquals(nx, m.getPosX());
        Assert.assertEquals(ny, m.getPosY());
    }
}
