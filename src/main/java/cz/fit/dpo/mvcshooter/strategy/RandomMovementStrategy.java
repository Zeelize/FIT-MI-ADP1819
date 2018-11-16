package cz.fit.dpo.mvcshooter.strategy;

import java.util.Random;

public class RandomMovementStrategy implements IMovementStrategy {
    Random rnd = new Random();

    @Override
    public int nextPosX(int initX, float initVel, float angle, long lifetime) {
        // todo fix random movement
        double radians = Math.toRadians(angle);
        return (int) (initX + (initVel) * lifetime * Math.cos(radians));
    }

    @Override
    public int nextPosY(int initY, float initVel, float angle, long lifetime) {
        // todo fix random
        double radians = Math.toRadians(angle);
        return rnd.nextInt(50) - 20 + (int) ((initY + (initVel) * lifetime * Math.sin(radians)) + 0.01 * (lifetime * lifetime));
    }
}
