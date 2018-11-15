package cz.fit.dpo.mvcshooter.strategy;

public class SimpleMovementStrategy implements IMovementStrategy {

    public int nextPosX(int initX, float initVel, float angle, long lifetime) {
        double radians = Math.toRadians(angle);
        return (int) (initX + (initVel) * lifetime * Math.cos(radians));


    }

    public int nextPosY(int initY, float initVel, float angle, long lifetime) {
        double radians = Math.toRadians(angle);
        return (int) ((initY + (initVel) * lifetime * Math.sin(radians)) + 0.01 * (lifetime * lifetime));
    }
}
