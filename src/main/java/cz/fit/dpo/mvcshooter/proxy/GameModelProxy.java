package cz.fit.dpo.mvcshooter.proxy;

import cz.fit.dpo.mvcshooter.command.AbsGameCommand;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.observer.IObserver;
import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;

import java.util.ArrayList;

public class GameModelProxy implements IGameModel {
    private IGameModel subject;

    public GameModelProxy(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    public int getScore() {
        return subject.getScore();
    }

    @Override
    public int getConfHeight() {
        return subject.getConfHeight();
    }

    @Override
    public int getConfWidth() {
        return subject.getConfWidth();
    }

    @Override
    public Cannon getCannon() {
        return subject.getCannon();
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return subject.getEnemies();
    }

    @Override
    public ArrayList<Missile> getMissiles() {
        return subject.getMissiles();
    }

    @Override
    public GameInfo getInfo() {
        return subject.getInfo();
    }

    @Override
    public void moveCannonDown() {
        subject.moveCannonDown();
    }

    @Override
    public void moveCannonUp() {
        subject.moveCannonUp();
    }

    @Override
    public void pauseResumeGame() {
        subject.pauseResumeGame();
    }

    @Override
    public void shootCanon() {
        subject.shootCanon();
    }

    @Override
    public void reloadCannon() {
        subject.reloadCannon();
    }

    @Override
    public void attachObserver(IObserver observer) {
        subject.attachObserver(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
        subject.detachObserver(observer);
    }

    @Override
    public void notifyObservers() {
        subject.notifyObservers();
    }

    @Override
    public void aimCanonUp() {
        subject.aimCanonUp();
    }

    @Override
    public void aimCanonDown() {
        subject.aimCanonDown();
    }

    @Override
    public void incCanonPower() {
        subject.incCanonPower();
    }

    @Override
    public void decCannonPower() {
        subject.decCannonPower();
    }

    @Override
    public void switchMovementStrategy() {
        subject.switchMovementStrategy();
    }

    @Override
    public ArrayList<GameObject> getGameObjects() {
        return subject.getGameObjects();
    }

    @Override
    public IMovementStrategy getActiveMovementStrategy() {
        return subject.getActiveMovementStrategy();
    }

    @Override
    public void toggleShootingMode() {
        subject.toggleShootingMode();
    }

    @Override
    public void registerCmd(AbsGameCommand cmd) {
        subject.registerCmd(cmd);
    }

    @Override
    public void undoLastCmd() {
        subject.undoLastCmd();
    }

    @Override
    public void setMemento(Object memento) {
        subject.setMemento(memento);
    }

    @Override
    public Object createMemento() {
        return this.subject.createMemento();
    }
}
