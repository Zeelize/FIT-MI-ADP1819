package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.config.GameConfig;

public class RealisticMovementStrategy implements IMovementStrategy {
    @Override
    public int nextPosX(int initX, float initVel, float angle, long lifetime) {
        double radians = Math.toRadians(angle);
        return (int) ((initX + (initVel) * lifetime * Math.cos(radians)) - GameConfig.BALISTIC_COEFFICIENT * (lifetime * lifetime));
    }

    @Override
    public int nextPosY(int initY, float initVel, float angle, long lifetime) {
        double radians = Math.toRadians(angle);
        return (int) ((initY + (initVel) * lifetime * Math.sin(radians)) + GameConfig.BALISTIC_COEFFICIENT * (lifetime * lifetime));
    }
}
