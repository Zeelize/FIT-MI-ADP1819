package cz.fit.dpo.mvcshooter.observer;

public interface IObservable {
    void attachObserver(IObserver observer);
    void detachObserver(IObserver observer);
    void notifyObservers();
}
