package cz.fit.dpo.mvcshooter.strategy;

public interface IMovementStrategy {
    int nextPosX(int initX, float initVel, float angle, long lifetime);

    int nextPosY(int initY, float initVel, float angle, long lifetime);
}
