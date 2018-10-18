package cz.fit.dpo.mvcshooter.observer;

public interface IObservable {
    void attachObserver(IObserver observer);
    void deattachObserver(IObserver observer);
    void notifyObservers();
}
